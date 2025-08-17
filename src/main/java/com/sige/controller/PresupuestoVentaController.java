package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.ParametroVenta;
import com.sige.model.Precio;
import com.sige.model.PresupuestoVenta;
import com.sige.model.Producto;
import com.sige.model.Vendedor;
import com.sige.repository.Cotizaciones;
import com.sige.repository.Depositos;
import com.sige.repository.ItemPrecios;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Precios;
import com.sige.repository.PresupuestoVentas;
import com.sige.repository.Vendedores;
import com.sige.repository.filter.PresupuestoVentaFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.PresupuestoVentaService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemPresupuestoVentaSession;

@Controller
@RequestMapping({"/presupuestoVentas"})
public class PresupuestoVentaController {
   @Autowired
   private PresupuestoVentas presupuestoVentas;
   @Autowired
   private PresupuestoVentaService presupuestoVentaService;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Precios precios;
   @Autowired
   private ParametroVentas parametroVentas;
   @Autowired
   private TablaItemPresupuestoVentaSession tablaItemPresupuestoVentaSession;
   @Autowired
   private Cotizaciones cotizaciones;
   @Autowired
   private ItemPrecios itemPrecios;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(PresupuestoVenta presupuestoVenta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("presupuestoVenta/presupuestoVenta");
      mv.addObject("vendedores", this.vendedores.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("precios", this.precios.findAll());
      this.adicionarDatosParametrizados(presupuestoVenta, usuarioSistema);
      this.agregarUUID(presupuestoVenta);
      mv.addObject("items", this.tablaItemPresupuestoVentaSession.getItems(presupuestoVenta.getUuid()));
      mv.addObject("total", this.tablaItemPresupuestoVentaSession.getTotal(presupuestoVenta.getUuid()));
      mv.addObject(presupuestoVenta);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid PresupuestoVenta presupuestoVenta, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/presupuestoVentas");
      presupuestoVenta.adicionarItem(this.tablaItemPresupuestoVentaSession.getItems(presupuestoVenta.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(presupuestoVenta, usuarioSistema);
      } else {
         try {
            this.presupuestoVentaService.guardar(presupuestoVenta);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(presupuestoVenta, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Presupuesto de venta se guardó con éxito! Nro: " + presupuestoVenta.getId());
         attributes.addFlashAttribute("registro", presupuestoVenta.getId());
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarPresupuesto(PresupuestoVentaFilter filter) {
      ModelAndView mv = new ModelAndView("presupuestoVenta/buscarPresupuestoVenta");
      mv.addObject(filter);
      mv.addObject("presupuestos", this.presupuestoVentas.getPresupuestoVentas(filter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      PresupuestoVenta presupuesto = (PresupuestoVenta)this.presupuestoVentas.findById(id).orElse(null);
      this.agregarUUID(presupuesto);

      for (ItemPresupuestoVenta item : presupuesto.getItems()) {
         this.tablaItemPresupuestoVentaSession.adicionarItem(item.getProducto(), item.getCantidad(), item.getCosto(), item.getPrecio(), presupuesto.getUuid());
      }

      return this.inicio(presupuesto, usuarioSistema);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> elimiar(@PathVariable Long id) {
      try {
         this.presupuestoVentaService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   private void agregarUUID(PresupuestoVenta presupuestoVenta) {
      if (StringUtils.isEmpty(presupuestoVenta.getUuid())) {
         presupuestoVenta.setUuid(UUID.randomUUID().toString());
      }
   }

   private void adicionarDatosParametrizados(PresupuestoVenta presupuestoVenta, UsuarioSistema usuarioSistema) {
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      if (presupuestoVenta.getCliente() == null) {
         presupuestoVenta.setCliente(parametroVenta.getCliente());
         if (parametroVenta.getCliente() != null) {
            presupuestoVenta.setPrecio(parametroVenta.getCliente().getPrecio());
            if (parametroVenta.getCliente().getPrecio() != null) {
               presupuestoVenta.setMoneda(parametroVenta.getCliente().getPrecio().getMoneda());
            }
         }
      }

      if (presupuestoVenta.getDeposito() == null) {
         presupuestoVenta.setDeposito(parametroVenta.getDeposito());
      }

      if (presupuestoVenta.getUsuario() == null) {
         presupuestoVenta.setUsuario(usuarioSistema.getUsuario());
         if (usuarioSistema.getUsuario().getVendedor() != null) {
            presupuestoVenta.setVendedor(usuarioSistema.getUsuario().getVendedor());
         }
      }
   }

   @PostMapping({"/adicionar/item"})
   public ModelAndView adicionarItem(
      Long monedaDestino,
      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha,
      Producto producto,
      BigDecimal cantidad,
      BigDecimal costo,
      BigDecimal precio,
      String uuid
   ) {
      BigDecimal costo_cotizado = this.cotizaciones.fCotizar(producto.getMoneda().getId(), monedaDestino, fecha, producto.getCosto(), BigDecimal.ZERO);
      this.tablaItemPresupuestoVentaSession.adicionarItem(producto, cantidad, costo_cotizado, precio, uuid);
      return this.getItemMv(uuid);
   }

   @PutMapping({"/modificar/cantidad"})
   public ModelAndView modificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      this.tablaItemPresupuestoVentaSession.modificarCantidad(producto, cantidad, uuid);
      return this.getItemMv(uuid);
   }

   @DeleteMapping({"/eliminar/item"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.tablaItemPresupuestoVentaSession.eliminarItem(indice, uuid);
      return this.getItemMv(uuid);
   }

   private ModelAndView getItemMv(String uuid) {
      ModelAndView mv = new ModelAndView("presupuestoVenta/itemPresupuestoVenta");
      mv.addObject("items", this.tablaItemPresupuestoVentaSession.getItems(uuid));
      mv.addObject("total", this.tablaItemPresupuestoVentaSession.getTotal(uuid));
      return mv;
   }

   @PutMapping({"/cambiar/precio"})
   public ModelAndView cambiarPrecio(Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Precio precio, String uuid) {
      for (ItemPresupuestoVenta item : this.tablaItemPresupuestoVentaSession.getItems(uuid)) {
         BigDecimal costo_cotizado = this.cotizaciones
            .fCotizar(item.getProducto().getMoneda().getId(), monedaDestino, fecha, item.getProducto().getCosto(), BigDecimal.ZERO);
         this.tablaItemPresupuestoVentaSession
            .modificarPrecioCosto(item.getProducto(), this.cambiarPrecioVenta(precio, item.getProducto()), costo_cotizado, uuid);
      }

      ModelAndView mv = this.getItemMv(uuid);
      return mv;
   }

   private BigDecimal cambiarPrecioVenta(Precio precio, Producto producto) {
      return this.itemPrecios.getPrecioProducto(precio, producto);
   }

   @GetMapping({"/js/buscar"})
   @ResponseBody
   public List<PresupuestoVenta> buscarPresu(
      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta, Vendedor vendedor
   ) {
      if (vendedor.getId() == null) {
         vendedor = null;
      }

      return this.presupuestoVentas.BuscarPresupuestoVenta(fechaDesde, fechaHasta, vendedor);
   }

   @GetMapping({"/js/getById"})
   @ResponseBody
   public PresupuestoVenta getByIdJs(Long id) {
      return (PresupuestoVenta)this.presupuestoVentas.findById(id).orElse(null);
   }

   @GetMapping({"/js/recuperar"})
   @ResponseBody
   public List<ItemPresupuestoVenta> getItemPresu(PresupuestoVenta presupuestoVenta) {
      return this.presupuestoVentas.getItemPresupuesto(presupuestoVenta);
   }
}
