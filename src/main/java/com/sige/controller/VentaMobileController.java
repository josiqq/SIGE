package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.sige.model.CondicionVenta;
import com.sige.model.ItemVenta;
import com.sige.model.ParametroVenta;
import com.sige.model.Producto;
import com.sige.model.Venta;
import com.sige.repository.Depositos;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Precios;
import com.sige.repository.Vendedores;
import com.sige.repository.Ventas;
import com.sige.repository.filter.VentaMobileFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.VentaService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemVentaSession;

@Controller
@RequestMapping({"/ventaMobiles"})
public class VentaMobileController {
   @Autowired
   private VentaService ventaService;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private Precios precios;
   @Autowired
   private ParametroVentas parametroVentas;
   @Autowired
   private TablaItemVentaSession tablaItemVentaSession;
   @Autowired
   private Ventas ventas;
   @Autowired
   private Monedas monedas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Venta venta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("venta/ventaMobile/ventaMobile");
      mv.addObject(venta);
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("condicionVentas", CondicionVenta.values());
      mv.addObject("monedas", this.monedas.findAll());
      this.adicionarUUID(venta);
      mv.addObject("items", this.tablaItemVentaSession.getItems(venta.getUuid()));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(venta.getUuid()));
      mv.addObject("lineas", this.tablaItemVentaSession.getItems(venta.getUuid()).size());
      this.agregarCondicionVenta(venta);
      this.adcionarDatosParametrizados(venta, usuarioSistema);
      return mv;
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(VentaMobileFilter ventaMobileFilter) {
      ModelAndView mv = new ModelAndView("venta/ventaMobile/buscarVentaMobile");
      mv.addObject("ventas", this.ventas.getVentaMobile(ventaMobileFilter));
      mv.addObject(ventaMobileFilter);
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Venta venta = this.ventaService.buscarPorId(id);
      this.adicionarUUID(venta);

      for (ItemVenta itemVenta : venta.getItems()) {
         if (!itemVenta.getProducto().isPesable()) {
            itemVenta.setCantidad(itemVenta.getCantidad().setScale(0, RoundingMode.HALF_UP));
         }

         this.tablaItemVentaSession
            .adicionarItem(itemVenta.getProducto(), itemVenta.getPrecio(), itemVenta.getCosto(), itemVenta.getCantidad(), venta.getUuid());
      }

      return this.inicio(venta, usuarioSistema);
   }

   @PostMapping
   public ModelAndView guardar(@Valid Venta venta, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("redirect:/ventaMobiles");
      venta.cargarItem(this.tablaItemVentaSession.getItems(venta.getUuid()));
      venta.calcularTotal();
      if (result.hasErrors()) {
         return this.inicio(venta, usuarioSistema);
      } else {
         try {
            this.ventaService.guardar(venta, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(venta, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Venta: " + venta.getId() + " guardada con exito!");
         attributes.addFlashAttribute("id_retorno", venta.getId());
         return mv;
      }
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminarVenta(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.ventaService.eliminar(id, usuarioSistema);
      } catch (NegocioException var4) {
         return ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/item/adicionar"})
   public ModelAndView adicionar(Producto producto, BigDecimal costo, BigDecimal precio, BigDecimal cantidad, String uuid) {
      if (!producto.isPesable()) {
         cantidad = cantidad.setScale(0, RoundingMode.HALF_UP);
      }

      this.tablaItemVentaSession.adicionarItem(producto, precio, costo, cantidad, uuid);
      return this.adicionarMv(uuid);
   }

   @PutMapping({"/item/sumar/cantidad"})
   public ModelAndView sumarCantidad(Producto producto, String uuid) {
      this.tablaItemVentaSession.sumarCantidad(producto, uuid);
      return this.adicionarMv(uuid);
   }

   @DeleteMapping({"/item/eliminar"})
   public ModelAndView eliminarItem(Producto producto, String uuid) {
      this.tablaItemVentaSession.eliminarItem(producto, uuid);
      return this.adicionarMv(uuid);
   }

   @PutMapping({"/item/restar/cantidad"})
   public ModelAndView restarCantidad(Producto producto, String uuid) {
      this.tablaItemVentaSession.restarCantidad(producto, uuid);
      return this.adicionarMv(uuid);
   }

   private ModelAndView adicionarMv(String uuid) {
      ModelAndView mv = new ModelAndView("venta/ventaMobile/itemVentaMobile");
      mv.addObject("items", this.tablaItemVentaSession.getItems(uuid));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(uuid));
      mv.addObject("lineas", this.tablaItemVentaSession.getItems(uuid).size());
      mv.addObject(mv);
      return mv;
   }

   private void adicionarUUID(Venta venta) {
      if (StringUtils.isEmpty(venta.getUuid())) {
         venta.setUuid(UUID.randomUUID().toString());
      }
   }

   private void adcionarDatosParametrizados(Venta venta, UsuarioSistema usuarioSistema) {
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      venta.setUsuario(usuarioSistema.getUsuario());
      if (venta.getPrecio() == null && parametroVenta.getPrecio() != null) {
         venta.setPrecio(parametroVenta.getPrecio());
      }

      if (venta.getDeposito() == null && parametroVenta.getDeposito() != null) {
         venta.setDeposito(parametroVenta.getDeposito());
      }

      if (venta.getCliente() == null && parametroVenta.getCliente() != null) {
         venta.setCliente(parametroVenta.getCliente());
      }

      if (venta.getVendedor() == null && usuarioSistema.getUsuario().getVendedor() != null) {
         venta.setVendedor(usuarioSistema.getUsuario().getVendedor());
      }

      if (parametroVenta.getMoneda() != null) {
         venta.setMoneda(parametroVenta.getMoneda());
      }

      if (parametroVenta.getCondicion() != null) {
         venta.setCondicion(parametroVenta.getCondicion());
      }
   }

   private void agregarCondicionVenta(Venta venta) {
      if (venta.getCondicionVenta() == null) {
         venta.setCondicionVenta(CondicionVenta.CONTADO);
      }
   }
}
