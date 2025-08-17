package com.sige.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.sige.model.Compra;
import com.sige.model.ItemCompra;
import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.Parametro;
import com.sige.model.Producto;
import com.sige.repository.Depositos;
import com.sige.repository.Monedas;
import com.sige.repository.Parametros;
import com.sige.repository.Precios;
import com.sige.repository.PresupuestoCompras;
import com.sige.repository.Productos;
import com.sige.repository.filter.CompraFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.CompraService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemCompraSession;

@Controller
@RequestMapping({"/compras"})
public class CompraController {
   @Autowired
   private Depositos depositos;
   @Autowired
   private Productos productos;
   @Autowired
   private TablaItemCompraSession tablaItemCompraSession;
   @Autowired
   private CompraService compraService;
   @Autowired
   private Precios precios;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private PresupuestoCompras presupuestos;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Compra compra, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("compra/compra");
      this.setUUID(compra);
      mv.addObject("items", this.tablaItemCompraSession.getItems(compra.getUuid()));
      mv.addObject("depositos", this.depositos.findAll());
      compra.setTotal(compra.getTotal());
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject(compra);
      this.agregarDatosParametrizados(compra);
      return mv;
   }

   private void agregarDatosParametrizados(Compra compra) {
      Parametro parametro = this.parametros.getParametro();
      if (compra.getMoneda() == null) {
         compra.setMoneda(parametro.getMoneda());
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid Compra compra, BindingResult result, RedirectAttributes attribute, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("redirect:/compras");
      compra.cargarItems(this.tablaItemCompraSession.getItems(compra.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(compra, usuarioSistema);
      } else {
         try {
            this.compraService.guardar(compra, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(compra, usuarioSistema);
         } catch (DataIntegrityViolationException var8) {
            result.rejectValue("", "", var8.getMessage());
            return this.inicio(compra, usuarioSistema);
         }

         attribute.addFlashAttribute("mensaje", "Compra guardada con éxito, registro nro: " + compra.getId());
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(CompraFilter compraFilter) {
      ModelAndView mv = new ModelAndView("compra/buscarCompra");
      mv.addObject("compras", this.compraService.buscarCompra(compraFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Compra compra = this.compraService.buscarPorId(id);
      this.setUUID(compra);

      for (ItemCompra item : compra.getItems()) {
         this.tablaItemCompraSession.adicionarItem(item.getProducto(), item.getCantidad(), item.getPrecio(), item.getPrecioVenta(), compra.getUuid());
      }

      ModelAndView mv = this.inicio(compra, usuarioSistema);
      mv.addObject(compra);
      return mv;
   }

   @PostMapping({"/item"})
   @ResponseBody
   public ModelAndView cargarItem(Long id, BigDecimal cantidad, BigDecimal precio, BigDecimal precioVenta, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.tablaItemCompraSession.adicionarItem(producto, cantidad, precio, precioVenta, uuid);
      return this.mvItemCompra(uuid);
   }

   @PutMapping({"/item/modificarCantidad/{id}"})
   public ModelAndView modificarItem(@PathVariable Long id, BigDecimal cantidad, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.tablaItemCompraSession.modificarCanidad(producto, cantidad, uuid);
      return this.mvItemCompra(uuid);
   }

   @PutMapping({"/item/modificarPrecio/{id}"})
   public ModelAndView modificarItemPrecio(@PathVariable Long id, BigDecimal precio, String uuid) {
      Producto producto = (Producto)this.productos.findById(id).orElse(null);
      this.tablaItemCompraSession.modificarPrecio(producto, precio, uuid);
      return this.mvItemCompra(uuid);
   }

   @PutMapping({"/item/modificarPrecioVenta"})
   public ModelAndView modificarItemPrecioVenta(Producto producto, BigDecimal precioVenta, String uuid) {
      this.tablaItemCompraSession.modificarPrecioVenta(producto, precioVenta, uuid);
      return this.mvItemCompra(uuid);
   }

   @DeleteMapping({"/item/eliminar/{uuid}/{id}"})
   public ModelAndView eliminarItem(@PathVariable("id") Producto producto, @PathVariable String uuid) {
      this.tablaItemCompraSession.eliminarItem(producto, uuid);
      return this.mvItemCompra(uuid);
   }

   @GetMapping({"/item"})
   public ModelAndView mostrarItem(String uuid) {
      ModelAndView mv = new ModelAndView("compra/itemCompra");
      mv.addObject("items", this.tablaItemCompraSession.getItems(uuid));
      mv.addObject("valorTotal", this.tablaItemCompraSession.getValorTotal(uuid));
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      this.compraService.eliminar(id, usuarioSistema);
      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/buscar/item/compra"})
   @ResponseBody
   public List<ItemCompra> getItemCompraByCompra(Compra compra) {
      return this.compraService.getItemCompraByCompra(compra);
   }

   private ModelAndView mvItemCompra(String uuid) {
      ModelAndView mv = new ModelAndView("compra/itemCompra");
      mv.addObject("items", this.tablaItemCompraSession.getItems(uuid));
      mv.addObject("valorTotal", this.tablaItemCompraSession.getValorTotal(uuid));
      return mv;
   }

   private void setUUID(Compra compra) {
      if (StringUtils.isEmpty(compra.getUuid())) {
         System.out.println("carga el uuid");
         compra.setUuid(UUID.randomUUID().toString());
      }
   }

   @PostMapping({"/adicionar/con/presu"})
   public ModelAndView adicionarConPresu(Long id, String uuid) {
      for (ItemPresupuestoCompra item : this.presupuestos.getItemPresupuesto(id)) {
         this.tablaItemCompraSession.adicionarItem(item.getProducto(), item.getCantidad(), item.getPrecio(), BigDecimal.ZERO, uuid);
      }

      return this.mvItemCompra(uuid);
   }
}
