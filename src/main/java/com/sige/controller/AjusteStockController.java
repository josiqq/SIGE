package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.AjusteStock;
import com.sige.model.Deposito;
import com.sige.model.ItemAjusteStock;
import com.sige.model.Producto;
import com.sige.repository.Depositos;
import com.sige.repository.filter.AjusteStockFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.AjusteStockService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemAjusteStockSession;

@Controller
@RequestMapping({"/ajusteStocks"})
public class AjusteStockController {
   @Autowired
   private Depositos depositos;
   @Autowired
   private TablaItemAjusteStockSession tablaItemAjusteStockSession;
   @Autowired
   private AjusteStockService ajusteStockService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(AjusteStock ajusteStock) {
      ModelAndView mv = new ModelAndView("ajusteStock/ajusteStock");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      this.agregarUUID(ajusteStock);
      mv.addObject(ajusteStock);
      mv.addObject("items", this.tablaItemAjusteStockSession.getItems(ajusteStock.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid AjusteStock ajusteStock, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/ajusteStocks");
      ajusteStock.agregarAjusteAItem(this.tablaItemAjusteStockSession.getItems(ajusteStock.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(ajusteStock);
      } else {
         try {
            this.ajusteStockService.guardar(ajusteStock, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(ajusteStock);
         }

         attributes.addFlashAttribute("mensaje", "Ajuste nro: " + ajusteStock.getId() + " guardada con éxisto");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarAjuste(AjusteStockFilter ajusteStockFilter) {
      ModelAndView mv = new ModelAndView("ajusteStock/buscarAjusteStock");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("ajusteStocks", this.ajusteStockService.buscarAjustes(ajusteStockFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      AjusteStock ajusteStock = this.ajusteStockService.buscarPorId(id);
      this.agregarUUID(ajusteStock);

      for (ItemAjusteStock item : ajusteStock.getItems()) {
         if (item.getProducto().isPesable()) {
            this.tablaItemAjusteStockSession.adicionarItem(item.getProducto(), item.getSumar(), item.getRestar(), item.getCantidad(), ajusteStock.getUuid());
         } else {
            this.tablaItemAjusteStockSession
               .adicionarItem(
                  item.getProducto(),
                  this.quitarDecimal(item.getSumar()),
                  this.quitarDecimal(item.getRestar()),
                  this.quitarDecimal(item.getCantidad()),
                  ajusteStock.getUuid()
               );
         }
      }

      return this.inicio(ajusteStock);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.ajusteStockService.eliminar(id, usuarioSistema);
      } catch (NegocioException var4) {
         return ResponseEntity.badRequest().body(var4.getCause().getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/item/adicionar"})
   public ModelAndView adicionarItem(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad, Deposito deposito, String uuid) {
      ModelAndView mv = new ModelAndView("ajusteStock/itemAjusteStock");
      this.rutinaDeCantidades(producto, sumar, restar, cantidad, deposito, uuid);
      mv.addObject("items", this.tablaItemAjusteStockSession.getItems(uuid));
      return mv;
   }

   private void rutinaDeCantidades(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad, Deposito deposito, String uuid) {
      BigDecimal cantidadActual = this.ajusteStockService.getCantidadStock(producto.getId(), deposito.getId());
      BigDecimal diferencia = BigDecimal.ZERO;
      if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
         if (cantidadActual.compareTo(cantidad) > 0) {
            diferencia = cantidadActual.subtract(cantidad);
            restar = diferencia;
            sumar = BigDecimal.ZERO;
         } else if (cantidadActual.compareTo(cantidad) < 0) {
            diferencia = cantidad.subtract(cantidadActual);
            sumar = diferencia;
            restar = BigDecimal.ZERO;
         } else if (cantidadActual.compareTo(cantidad) == 0) {
            sumar = BigDecimal.ZERO;
            restar = BigDecimal.ZERO;
         }
      } else if (cantidad.compareTo(BigDecimal.ZERO) < 0) {
         if (cantidadActual.compareTo(cantidad) > 0) {
            diferencia = cantidadActual.add(cantidad);
            restar = diferencia;
            sumar = BigDecimal.ZERO;
         } else if (cantidadActual.compareTo(cantidad) < 0) {
            diferencia = cantidad.add(cantidadActual);
            sumar = diferencia;
            restar = BigDecimal.ZERO;
         } else if (cantidadActual.compareTo(cantidad) == 0) {
            sumar = BigDecimal.ZERO;
            restar = BigDecimal.ZERO;
         }
      } else if (restar.compareTo(BigDecimal.ZERO) > 0) {
         sumar = BigDecimal.ZERO;
         cantidad = cantidadActual.subtract(restar);
      } else if (sumar.compareTo(BigDecimal.ZERO) > 0) {
         restar = BigDecimal.ZERO;
         cantidad = cantidadActual.add(sumar);
      }

      this.tablaItemAjusteStockSession.adicionarItem(producto, sumar, restar, cantidad, uuid);
   }

   @PutMapping({"/item/modificar"})
   public ModelAndView modificarItem(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad, Deposito deposito, String uuid) {
      ModelAndView mv = new ModelAndView("ajusteStock/itemAjusteStock");
      this.rutinaDeCantidades(producto, sumar, restar, cantidad, deposito, uuid);
      mv.addObject("items", this.tablaItemAjusteStockSession.getItems(uuid));
      return mv;
   }

   @DeleteMapping({"/item/eliminar"})
   public ModelAndView eliminar(Producto producto, String uuid) {
      ModelAndView mv = new ModelAndView("ajusteStock/itemAjusteStock");
      this.tablaItemAjusteStockSession.eliminarItem(producto, uuid);
      mv.addObject("items", this.tablaItemAjusteStockSession.getItems(uuid));
      return mv;
   }

   @GetMapping({"/item/arraySize"})
   @ResponseBody
   public List<ItemAjusteStock> getItems(String uuid) {
      return this.tablaItemAjusteStockSession.getItems(uuid);
   }

   @GetMapping({"/get-cantidad-actual"})
   @ResponseBody
   public ResponseEntity<?> getCantidadActual(Long producto, Long deposito) {
      BigDecimal total = BigDecimal.ZERO;

      try {
         total = this.ajusteStockService.getCantidadStock(producto, deposito);
      } catch (DataAccessException var7) {
         Throwable rootCause = var7.getRootCause();
         String errorMessage = "Error de la base de datos: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok(total);
   }

   private void agregarUUID(AjusteStock ajusteStock) {
      if (StringUtils.isEmpty(ajusteStock.getUuid())) {
         ajusteStock.setUuid(UUID.randomUUID().toString());
      }
   }

   private BigDecimal quitarDecimal(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }

   private BigDecimal getDiferenciaCantidad(BigDecimal stockActual, BigDecimal cantidad) {
      return stockActual.subtract(cantidad);
   }

   private void sumarOrestar(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad, String uuid, BigDecimal resultado) {
      if (resultado.compareTo(BigDecimal.ZERO) < 0) {
         resultado = resultado.multiply(new BigDecimal("-1"));
         if (producto.isPesable()) {
            this.tablaItemAjusteStockSession.adicionarItem(producto, resultado, restar, cantidad, uuid);
         } else {
            this.tablaItemAjusteStockSession
               .adicionarItem(producto, this.quitarDecimal(resultado), this.quitarDecimal(restar), this.quitarDecimal(cantidad), uuid);
         }
      } else if (producto.isPesable()) {
         this.tablaItemAjusteStockSession.adicionarItem(producto, sumar, resultado, cantidad, uuid);
      } else {
         this.tablaItemAjusteStockSession.adicionarItem(producto, this.quitarDecimal(sumar), this.quitarDecimal(resultado), this.quitarDecimal(cantidad), uuid);
      }
   }
}
