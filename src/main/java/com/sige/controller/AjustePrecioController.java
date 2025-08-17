package com.sige.controller;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.sige.model.AjustePrecio;
import com.sige.model.Compra;
import com.sige.model.ItemAjustePrecio;
import com.sige.model.ItemCompra;
import com.sige.model.ItemPrecio;
import com.sige.model.Precio;
import com.sige.model.Producto;
import com.sige.repository.ItemCompras;
import com.sige.repository.ItemPrecios;
import com.sige.repository.Precios;
import com.sige.repository.Productos;
import com.sige.repository.filter.AjustePrecioFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.AjustePrecioService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemAjustePrecioSession;

@Controller
@RequestMapping({"/ajustePrecios"})
public class AjustePrecioController {
   @Autowired
   private Precios precios;
   @Autowired
   private TablaItemAjustePrecioSession itemAjustePrecioSession;
   @Autowired
   private Productos productos;
   @Autowired
   private AjustePrecioService ajustePrecioService;
   @Autowired
   private ItemPrecios itemPrecios;
   @Autowired
   private ItemCompras itemCompras;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(AjustePrecio ajustePrecio) {
      ModelAndView mv = new ModelAndView("ajustePrecio/ajustePrecio");
      this.agregarUUID(ajustePrecio);
      mv.addObject(ajustePrecio);
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("items", this.itemAjustePrecioSession.getItems(ajustePrecio.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid AjustePrecio ajustePrecio, BindingResult result, RedirectAttributes attribute, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/ajustePrecios");
      ajustePrecio.cargarItemAjustePrecio(this.itemAjustePrecioSession.getItems(ajustePrecio.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(ajustePrecio);
      } else {
         try {
            this.ajustePrecioService.guardar(ajustePrecio, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(ajustePrecio);
         }

         attribute.addFlashAttribute("mensaje", "Ajuste realizada con éxito");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(AjustePrecioFilter ajustePrecioFilter) {
      ModelAndView mv = new ModelAndView("ajustePrecio/buscarAjustePrecio");
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("ajustePrecios", this.ajustePrecioService.buscarAjuste(ajustePrecioFilter));
      return mv;
   }

   @PostMapping({"/item"})
   public ModelAndView agregarItem(Long id, BigDecimal costo, BigDecimal precioMinimo, BigDecimal precioProducto, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.itemAjustePrecioSession.adicionarItem(producto, costo, precioMinimo, precioProducto, uuid);
      return this.mvItemAjustePrecio(uuid);
   }

   @PutMapping({"/item/modificarPrecioMinimo"})
   public ModelAndView modificarPrecioMinimo(Long id, BigDecimal precioMinimo, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.itemAjustePrecioSession.modificarItemPrecioMinimo(producto, precioMinimo, uuid);
      return this.mvItemAjustePrecio(uuid);
   }

   @PutMapping({"/item/modificarPrecioProducto"})
   public ModelAndView modificarPrecioProucto(Long id, BigDecimal precioProducto, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.itemAjustePrecioSession.modificarItemPrecioProducto(producto, precioProducto, uuid);
      return this.mvItemAjustePrecio(uuid);
   }

   @DeleteMapping({"/item/eliminar/{uuid}/{id}"})
   public ModelAndView eliminarItem(@PathVariable("id") Producto producto, @PathVariable String uuid) {
      this.itemAjustePrecioSession.eliminarItem(producto, uuid);
      return this.mvItemAjustePrecio(uuid);
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      AjustePrecio ajustePrecio = this.ajustePrecioService.buscarPorId(id);
      this.agregarUUID(ajustePrecio);

      for (ItemAjustePrecio itemAjustePrecio : ajustePrecio.getItems()) {
         this.itemAjustePrecioSession
            .adicionarItem(
               itemAjustePrecio.getProducto(),
               itemAjustePrecio.getCosto(),
               itemAjustePrecio.getPrecioMinimo(),
               itemAjustePrecio.getPrecioProducto(),
               ajustePrecio.getUuid()
            );
      }

      return this.inicio(ajustePrecio);
   }

   @GetMapping({"/desplegarItems/{uuid}"})
   public ModelAndView desplegarItems(@PathVariable String uuid) {
      return this.mvItemAjustePrecio(uuid);
   }

   @PostMapping({"/items/adicionarTodos"})
   public ModelAndView adicionarTodos(Precio precio, String uuid) {
      for (Producto producto : this.productos.buscarTodoActivo()) {
         try {
            ItemPrecio itemPrecio = this.itemPrecios.buscarprecioProducto(precio, producto);
            this.itemAjustePrecioSession.adicionarItem(producto, itemPrecio.getCosto(), itemPrecio.getPrecioMinimo(), itemPrecio.getPrecioProducto(), uuid);
         } catch (Exception var7) {
            this.itemAjustePrecioSession.adicionarItem(producto, producto.getCosto(), BigDecimal.ZERO, BigDecimal.ZERO, uuid);
         }
      }

      ModelAndView mv = this.mvItemAjustePrecio(uuid);
      return mv;
   }

   @PostMapping({"/items/adicionarCompra"})
   public ModelAndView adicionarCompra(Compra compra, Precio precio, String uuid) {
      for (ItemCompra itemCompra : this.itemCompras.buscarDetalleCompra(compra)) {
         try {
            ItemPrecio itemPrecio = this.itemPrecios.buscarprecioProducto(precio, itemCompra.getProducto());
            this.itemAjustePrecioSession
               .adicionarItem(itemCompra.getProducto(), itemPrecio.getCosto(), itemPrecio.getPrecioMinimo(), itemPrecio.getPrecioProducto(), uuid);
         } catch (Exception var8) {
            this.itemAjustePrecioSession.adicionarItem(itemCompra.getProducto(), itemCompra.getProducto().getCosto(), BigDecimal.ZERO, BigDecimal.ZERO, uuid);
         }
      }

      ModelAndView mv = this.mvItemAjustePrecio(uuid);
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.ajustePrecioService.eliminar(id, usuarioSistema);
      } catch (NegocioException var4) {
         return ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   private void agregarUUID(AjustePrecio ajustePrecio) {
      if (StringUtils.isEmpty(ajustePrecio.getUuid())) {
         ajustePrecio.setUuid(UUID.randomUUID().toString());
      }
   }

   private ModelAndView mvItemAjustePrecio(String uuid) {
      ModelAndView mv = new ModelAndView("ajustePrecio/itemAjustePrecio");
      mv.addObject("items", this.itemAjustePrecioSession.getItems(uuid));
      return mv;
   }
}
