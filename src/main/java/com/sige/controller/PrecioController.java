package com.sige.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.sige.model.ItemPrecio;
import com.sige.model.Precio;
import com.sige.model.Producto;
import com.sige.repository.ItemPrecios;
import com.sige.repository.Monedas;
import com.sige.repository.Productos;
import com.sige.service.PrecioService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemPrecioSession;

@Controller
@RequestMapping({"/precios"})
public class PrecioController {
   @Autowired
   private TablaItemPrecioSession tablaItemPrecioSession;
   @Autowired
   private Productos productos;
   @Autowired
   private PrecioService precioService;
   @Autowired
   private ItemPrecios itemPrecios;
   @Autowired
   private Monedas monedas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Precio precio) {
      ModelAndView mv = new ModelAndView("precio/precio");
      this.setUUID(precio);
      mv.addObject(precio);
      mv.addObject("monedas", this.monedas.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Precio precio, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/precios");
      if (result.hasErrors()) {
         return this.inicio(precio);
      } else {
         precio.cargarItemPrecio(this.tablaItemPrecioSession.getItems(precio.getUuid()));

         try {
            this.precioService.guardar(precio);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(precio);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se han guardado con exito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarPrecio() {
      ModelAndView mv = new ModelAndView("precio/buscarPrecio");
      mv.addObject("precios", this.precioService.buscarPrecio());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Precio precio = this.precioService.buscarPorId(id);
      this.setUUID(precio);

      for (ItemPrecio item : precio.getItems()) {
         this.tablaItemPrecioSession.adicionarItem(item.getProducto(), item.getCosto(), item.getPrecioProducto(), precio.getUuid());
      }

      return this.inicio(precio);
   }

   @PostMapping({"/item"})
   public ModelAndView adicionarItem(Long id, BigDecimal costo, BigDecimal precioProducto, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.tablaItemPrecioSession.adicionarItem(producto, costo, precioProducto, uuid);
      return this.mvItemPrecio(uuid);
   }

   @PutMapping({"/item/modificarPrecioProducto/{id}"})
   public ModelAndView modificarPrecioProducto(@PathVariable("id") Producto producto, BigDecimal precioProducto, String uuid) {
      this.tablaItemPrecioSession.modificarItem(producto, precioProducto, uuid);
      return this.mvItemPrecio(uuid);
   }

   @DeleteMapping({"/item/eliminar/{uuid}/{id}"})
   public ModelAndView eliminarItem(@PathVariable("id") Producto producto, @PathVariable String uuid) {
      this.tablaItemPrecioSession.eliminarItem(producto, uuid);
      return this.mvItemPrecio(uuid);
   }

   @PostMapping({"/item/cargarTodo"})
   public ModelAndView cargarTodo(String uuid) {
      for (Producto producto : this.productos.findAll()) {
         this.tablaItemPrecioSession.adicionarItem(producto, producto.getCosto(), BigDecimal.ZERO, uuid);
      }

      ModelAndView mv = this.mvItemPrecio(uuid);
      return mv;
   }

   @GetMapping({"/item/recuperar"})
   public ModelAndView recuperarItem(String uuid) {
      return this.mvItemPrecio(uuid);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      this.precioService.eliminar(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscarPrecio/js/"})
   @ResponseBody
   public ItemPrecio buscarItemPrecio(Precio precio, Producto producto) {
      new ItemPrecio();

      ItemPrecio itemPrecio;
      try {
         itemPrecio = this.itemPrecios.buscarprecioProducto(precio, producto);
      } catch (Exception var5) {
         itemPrecio = new ItemPrecio();
      }

      return itemPrecio;
   }

   @GetMapping({"/buscarPrecioProducto"})
   @ResponseBody
   public List<ItemPrecio> buscarPrecioProducto(String nombreCodigo, Precio precio) {
      return this.itemPrecios.buscarPorProductoPrecio(nombreCodigo, precio);
   }

   @GetMapping({"/buscarPrecioPorCodigo"})
   @ResponseBody
   public ItemPrecio buscarPrecioPorCodigo(String codigo, Precio precio) {
      new ItemPrecio();

      ItemPrecio itemPrecio;
      try {
         itemPrecio = this.itemPrecios.buscarPrecioPorCodigo(codigo, precio);
      } catch (Exception var5) {
         itemPrecio = new ItemPrecio();
      }

      return itemPrecio;
   }

   @GetMapping({"/porProducto"})
   @ResponseBody
   public List<ItemPrecio> buscarPorProducto(Producto producto) {
      return this.itemPrecios.buscarPorProducto(producto);
   }

   @GetMapping({"/js/precioProducto"})
   @ResponseBody
   public BigDecimal getPrecioProducto(Precio precio, Producto producto) {
      BigDecimal precioRetorno = BigDecimal.ZERO;

      try {
         precioRetorno = this.itemPrecios.getPrecioProducto(precio, producto);
      } catch (Exception var5) {
         precioRetorno = BigDecimal.ZERO;
      }

      return precioRetorno;
   }

   private ModelAndView mvItemPrecio(String uuid) {
      ModelAndView mv = new ModelAndView("precio/ItemPrecio");
      mv.addObject("items", this.tablaItemPrecioSession.getItems(uuid));
      return mv;
   }

   private void setUUID(Precio precio) {
      if (StringUtils.isEmpty(precio.getUuid())) {
         precio.setUuid(UUID.randomUUID().toString());
      }
   }
}
