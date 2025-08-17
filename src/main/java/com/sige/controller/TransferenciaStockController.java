package com.sige.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
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
import org.thymeleaf.util.StringUtils;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.ItemTransferenciaStock;
import com.sige.model.Producto;
import com.sige.model.TransferenciaStock;
import com.sige.repository.Depositos;
import com.sige.repository.TransferenciaStocks;
import com.sige.repository.filter.TransferenciaStockFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.TransferenciaStockService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemTransferenciaStockSession;

@Controller
@RequestMapping({"/transferencia-stock"})
public class TransferenciaStockController {
   @Autowired
   private Depositos depositos;
   @Autowired
   private TransferenciaStocks transferenciaStocks;
   @Autowired
   private TablaItemTransferenciaStockSession session;
   @Autowired
   private TransferenciaStockService service;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(TransferenciaStock transferenciaStock, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("transferenciaStock/transferenciaStock");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      this.adicionarUUID(transferenciaStock, usuarioSistema);
      mv.addObject("items", this.session.getItems(transferenciaStock.getUuid()));
      mv.addObject(transferenciaStock);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid TransferenciaStock transferenciaStock, BindingResult result, RedirectAttributes attribute, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/transferencia-stock");
      transferenciaStock.adicionarTransferencia(this.session.getItems(transferenciaStock.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(transferenciaStock, usuarioSistema);
      } else {
         try {
            this.service.guardar(transferenciaStock);
         } catch (NegocioException var9) {
            result.rejectValue("", "", var9.getMessage());
            return this.inicio(transferenciaStock, usuarioSistema);
         } catch (DataAccessException var10) {
            Throwable rootCause = var10.getRootCause();
            String errorMessage = "Error de la base de datos: " + rootCause.getMessage();
            result.rejectValue("", "", errorMessage);
            return this.inicio(transferenciaStock, usuarioSistema);
         }

         attribute.addFlashAttribute("mensaje", "Transferencia guardada con éxito!  " + transferenciaStock.getId());
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      TransferenciaStock transferenciaStock = (TransferenciaStock)this.transferenciaStocks.findById(id).orElse(null);
      this.adicionarUUID(transferenciaStock, usuarioSistema);

      for (ItemTransferenciaStock item : transferenciaStock.getItems()) {
         this.session.adicionarItem(item.getProducto(), item.getCantidad(), transferenciaStock.getUuid());
      }

      return this.inicio(transferenciaStock, usuarioSistema);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarTransferencia(TransferenciaStockFilter filter) {
      ModelAndView mv = new ModelAndView("transferenciaStock/buscarTransferenciaStock");
      mv.addObject(filter);
      mv.addObject("transferencias", this.transferenciaStocks.getTransferencias(filter));
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.service.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/get/item-transferecnia-stock"})
   @ResponseBody
   public ResponseEntity<?> getItemTransferenciaStockByTransferencia(TransferenciaStock transferenciaStock) {
      new ArrayList();

      List items;
      try {
         items = this.transferenciaStocks.getAllItemsByTransferencia(transferenciaStock);
      } catch (DataAccessException var6) {
         Throwable rootCause = var6.getRootCause();
         String errorMessage = "Error en la base de datos: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok(items);
   }

   @PostMapping({"/item-adicionar"})
   public ModelAndView adicionarItem(Producto producto, BigDecimal cantidad, String uuid) {
      this.session.adicionarItem(producto, cantidad, uuid);
      return this.getMV(uuid);
   }

   @PutMapping({"/item-modificar-cantidad"})
   public ModelAndView mdificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      this.session.modificarCantidad(producto, cantidad, uuid);
      return this.getMV(uuid);
   }

   @DeleteMapping({"/item-eliminar"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.session.eliminarItem(indice, uuid);
      return this.getMV(uuid);
   }

   private ModelAndView getMV(String uuid) {
      ModelAndView mv = new ModelAndView("transferenciaStock/itemTransferenciaStock");
      mv.addObject("items", this.session.getItems(uuid));
      return mv;
   }

   private void adicionarUUID(TransferenciaStock transferenciaStock, UsuarioSistema usuarioSistema) {
      if (StringUtils.isEmpty(transferenciaStock.getUuid())) {
         transferenciaStock.setUuid(UUID.randomUUID().toString());
      }

      if (transferenciaStock.getUsuario() == null) {
         transferenciaStock.setUsuario(usuarioSistema.getUsuario());
      }
   }
}
