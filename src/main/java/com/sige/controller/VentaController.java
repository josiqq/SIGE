package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.dto.FacturaDto;
import com.sige.model.CondicionVenta;
import com.sige.model.Cuenta;
import com.sige.model.Deposito;
import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.ItemVenta;
import com.sige.model.ItemVentaLote;
import com.sige.model.ParametroVenta;
import com.sige.model.Precio;
import com.sige.model.PresupuestoVenta;
import com.sige.model.Producto;
import com.sige.model.Usuario;
import com.sige.model.Venta;
import com.sige.repository.Consorcios;
import com.sige.repository.Cotizaciones;
import com.sige.repository.Cuentas;
import com.sige.repository.Depositos;
import com.sige.repository.ItemPrecios;
import com.sige.repository.ItemVentas;
import com.sige.repository.Lotes;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Precios;
import com.sige.repository.PresupuestoVentas;
import com.sige.repository.Usuarios;
import com.sige.repository.Vendedores;
import com.sige.repository.Ventas;
import com.sige.repository.filter.ItemVentaFilter;
import com.sige.repository.filter.VentaFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.VentaService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.TablaItemVentaLoteSession;
import com.sige.session.TablaItemVentaSession;

@Controller
@RequestMapping({"/ventas"})
public class VentaController {
   @Autowired
   private Depositos depositos;
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private Usuarios usuarios;
   @Autowired
   private Precios precios;
   @Autowired
   private ParametroVentas parametroVentas;
   @Autowired
   private TablaItemVentaSession tablaItemVentaSession;
   @Autowired
   private VentaService ventaService;
   @Autowired
   private ItemVentas itemVentas;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private Consorcios consorcios;
   @Autowired
   private Monedas monedas;
   @Autowired
   private ItemPrecios itemPrecios;
   @Autowired
   private Cotizaciones cotizaciones;
   @Autowired
   private Ventas ventas;
   @Autowired
   private PresupuestoVentas presupuestos;
   @Autowired
   private TablaItemVentaLoteSession tablaItemVentaLoteSession;
   @Autowired
   private Lotes lotes;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Venta venta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("venta/venta");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      mv.addObject("vendedoresSupervisor", this.vendedores.getVendedorSupervisor());
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("condicionVentas", CondicionVenta.values());
      mv.addObject("consorcios", this.consorcios.getConsorciosPendientes());
      mv.addObject("monedas", this.monedas.findAll());
      this.agregarCondicionVenta(venta);
      this.agregarUsuarioVendedor(venta, usuarioSistema);
      this.agregarDatosParametrizados(venta);
      this.agregarUUID(venta);
      venta.setTotal(venta.getTotal());
      mv.addObject("items", this.tablaItemVentaSession.getItems(venta.getUuid()));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(venta.getUuid()));
      mv.addObject(venta);
      return mv;
   }

   private void agregarCondicionVenta(Venta venta) {
      if (venta.getCondicionVenta() == null) {
         venta.setCondicionVenta(CondicionVenta.CONTADO);
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid Venta venta, BindingResult result, RedirectAttributes attribute, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("redirect:/ventas");
      venta.cargarItem(this.tablaItemVentaSession.getItems(venta.getUuid()));
      venta.cargarItemsLotes(this.tablaItemVentaLoteSession.getItems(venta.getUuid()));
      venta.calcularTotal();
      if (result.hasErrors()) {
         return this.inicio(venta, usuarioSistema);
      } else {
         try {
            this.ventaService.guardar(venta, usuarioSistema);
         } catch (NegocioException var9) {
            result.rejectValue("", "", var9.getMessage());
            return this.inicio(venta, usuarioSistema);
         } catch (DataAccessException var10) {
            Throwable rootCause = var10.getRootCause();
            String messageError = "Error en la base de datos: " + rootCause.getMessage();
            result.rejectValue("", "", messageError);
            return this.inicio(venta, usuarioSistema);
         }

         attribute.addFlashAttribute("mensaje", "Venta guardada con exito! registro: " + venta.getId());
         attribute.addFlashAttribute("registro", venta.getId());
         attribute.addFlashAttribute("facturaRetorno", venta.isFactura());
         attribute.addFlashAttribute("condicionVenta", venta.getCondicionVenta());
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarVenta(VentaFilter ventaFilter) {
      ModelAndView mv = new ModelAndView("venta/buscarVenta");
      mv.addObject("ventas", this.ventaService.buscarVenta(ventaFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Venta venta = this.ventaService.buscarPorId(id);
      this.agregarUUID(venta);

      for (ItemVenta itemVenta : venta.getItems()) {
         this.tablaItemVentaSession
            .adicionarItem(itemVenta.getProducto(), itemVenta.getPrecio(), itemVenta.getCosto(), itemVenta.getCantidad(), venta.getUuid());
      }

      for (ItemVentaLote itemVentaLote : venta.getItemsLote()) {
         this.tablaItemVentaLoteSession
            .adicionarItem(
               itemVentaLote.getProducto(),
               itemVentaLote.getNroLote(),
               itemVentaLote.getVencimiento(),
               itemVentaLote.getCantidad(),
               itemVentaLote.getCantidadActual(),
               venta.getUuid()
            );
      }

      return this.inicio(venta, usuarioSistema);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.ventaService.eliminar(id, usuarioSistema);
      } catch (NegocioException var4) {
         return ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/item/adicionar"})
   @ResponseBody
   public ModelAndView adicionarItem(
      Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Producto producto, BigDecimal precio, BigDecimal cantidad, String uuid
   ) {
      BigDecimal costo_cotizado = this.cotizaciones.fCotizar(producto.getMoneda().getId(), monedaDestino, fecha, producto.getCosto(), BigDecimal.ZERO);
      this.tablaItemVentaSession.adicionarItem(producto, precio, costo_cotizado, cantidad, uuid);
      return this.mvItemVenta(uuid);
   }

   @PutMapping({"/item/modificar/cantidad"})
   @ResponseBody
   public ModelAndView modificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      if (producto.isPesable()) {
         this.tablaItemVentaSession.modificarCantidad(producto, cantidad, uuid);
      } else {
         this.tablaItemVentaSession.modificarCantidad(producto, this.quitarDecima(cantidad), uuid);
      }

      return this.mvItemVenta(uuid);
   }

   @PutMapping({"/item/modificar/precio"})
   @ResponseBody
   public ModelAndView modificarPrecio(Producto producto, BigDecimal precio, String uuid) {
      this.tablaItemVentaSession.modificarPrecio(producto, this.quitarDecima(precio), uuid);
      return this.mvItemVenta(uuid);
   }

   @DeleteMapping({"/item/eliminar"})
   @ResponseBody
   public ModelAndView eliminar(Producto producto, String uuid) {
      this.tablaItemVentaSession.eliminarItem(producto, uuid);
      return this.mvItemVenta(uuid);
   }

   private BigDecimal quitarDecima(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }

   @GetMapping({"/js/recuperar"})
   @ResponseBody
   public List<ItemVenta> buscarItemVenta(Venta venta) {
      return this.itemVentas.buscarPorVenta(venta);
   }

   @GetMapping({"/totalesVentas"})
   public ModelAndView totalVentas(ItemVentaFilter itemVentaFilter) {
      ModelAndView mv = new ModelAndView("reporte/venta/totalesVentas");
      BigDecimal totalUtilidad = BigDecimal.ZERO;
      BigDecimal totalCosto = BigDecimal.ZERO;
      BigDecimal totalPrecio = BigDecimal.ZERO;
      List<Object[]> ventas = this.itemVentas.totalesVenta(itemVentaFilter);
      totalUtilidad = this.quitarDecima(ventas.stream().map(obj -> (BigDecimal)obj[7]).reduce(BigDecimal.ZERO, BigDecimal::add));
      totalPrecio = this.quitarDecima(ventas.stream().map(obj -> (BigDecimal)obj[6]).reduce(BigDecimal.ZERO, BigDecimal::add));
      totalCosto = this.quitarDecima(ventas.stream().map(obj -> (BigDecimal)obj[5]).reduce(BigDecimal.ZERO, BigDecimal::add));
      mv.addObject("totalPrecio", totalPrecio);
      mv.addObject("totalCosto", totalCosto);
      mv.addObject("totalUtilidad", totalUtilidad);
      mv.addObject("totalVentas", this.itemVentas.totalesVenta(itemVentaFilter));
      return mv;
   }

   @GetMapping({"/js/getFactura"})
   @ResponseBody
   public List<FacturaDto> getFactura(Long id) {
      return this.ventaService.getFacturas(id);
   }

   @PostMapping({"/js/agregar/timbrado"})
   @ResponseBody
   public ResponseEntity<?> agregarTimbrado(String uuid, Venta venta) {
      Cuenta cuenta = this.cuentas.getCuentaByExpedicion(uuid);
      Integer nroFactura;
      if (this.ventaService.getNroFactura(cuenta.getTimbrado()) == null) {
         nroFactura = cuenta.getTimbrado().getNumeracionIni();
      } else {
         nroFactura = this.ventaService.getNroFactura(cuenta.getTimbrado()) + 1;
      }

      this.ventaService.updateNroFactura(venta, cuenta.getTimbrado(), nroFactura);
      return ResponseEntity.ok().build();
   }

   @PutMapping({"/js/update/impreso"})
   @ResponseBody
   public ResponseEntity<?> updateImpreso(Long id) {
      this.ventaService.updateImpreso(id);
      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/buscar/item/venta"})
   @ResponseBody
   public List<ItemVenta> getItemVentaByVenta(Venta venta) {
      return this.ventaService.getItemVentaByVenta(venta);
   }

   private void agregarUUID(Venta venta) {
      if (StringUtils.isEmpty(venta.getUuid())) {
         venta.setUuid(UUID.randomUUID().toString());
      }
   }

   private void agregarDatosParametrizados(Venta venta) {
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      if (venta.getCliente() == null) {
         venta.setCliente(parametroVenta.getCliente());
      }

      if (venta.getPrecio() == null) {
         venta.setPrecio(venta.getCliente().getPrecio());
      }

      if (venta.getDeposito() == null) {
         venta.setDeposito(parametroVenta.getDeposito());
      }

      if (venta.getCondicion() == null) {
         venta.setCondicion(parametroVenta.getCondicion());
      }

      if (venta.getMoneda() == null && venta.getId() == null) {
         venta.setMoneda(venta.getCliente().getPrecio().getMoneda());
      }
   }

   private void agregarUsuarioVendedor(Venta venta, UsuarioSistema usuarioSistema) {
      if (venta.getUsuario() == null) {
         Usuario usuario = this.usuarios.buscarUsuario(usuarioSistema.getUsername());
         venta.setUsuario(usuario);
         if (usuario.getVendedor() != null) {
            venta.setVendedor(usuario.getVendedor());
         }
      }
   }

   private ModelAndView mvItemVenta(String uuid) {
      ModelAndView mv = new ModelAndView("venta/itemVenta");
      mv.addObject("items", this.tablaItemVentaSession.getItems(uuid));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(uuid));
      return mv;
   }

   @PutMapping({"/cambiar/precio"})
   public ModelAndView cambiarPrecio(Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Precio precio, String uuid) {
      for (ItemVenta item : this.tablaItemVentaSession.getItems(uuid)) {
         BigDecimal costo_cotizado = this.cotizaciones
            .fCotizar(item.getProducto().getMoneda().getId(), monedaDestino, fecha, item.getProducto().getCosto(), BigDecimal.ZERO);
         this.tablaItemVentaSession.modificarPrecioCosto(item.getProducto(), this.cambiarPrecioVenta(precio, item.getProducto()), costo_cotizado, uuid);
      }

      ModelAndView mv = this.mvItemVenta(uuid);
      return mv;
   }

   private BigDecimal cambiarPrecioVenta(Precio precio, Producto producto) {
      return this.itemPrecios.getPrecioProducto(precio, producto);
   }

   @PostMapping({"/adicionar/con/presu"})
   public ModelAndView adicionarConPresu(PresupuestoVenta presupuestoVenta, String uuid) {
      for (ItemPresupuestoVenta item : this.presupuestos.getItemPresupuesto(presupuestoVenta)) {
         this.tablaItemVentaSession.adicionarItem(item.getProducto(), item.getPrecio(), item.getCosto(), item.getCantidad(), uuid);
      }

      ModelAndView mv = this.mvItemVenta(uuid);
      return mv;
   }

   @GetMapping({"/notaCreditoVentas"})
   public ModelAndView notaCredito(Venta venta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("notaCreditoVenta/notaCreditoVenta");
      mv.addObject(venta);
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("depositos", this.depositos.findAll());
      mv.addObject("vendedores", this.vendedores.findAll());
      mv.addObject("precios", this.precios.findAll());
      venta.setNc(true);
      this.agregarDatosParametrizados(venta);
      venta.setCondicionVenta(CondicionVenta.CREDITO);
      this.agregarUsuarioVendedor(venta, usuarioSistema);
      this.agregarUUID(venta);
      mv.addObject("items", this.tablaItemVentaSession.getItems(venta.getUuid()));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(venta.getUuid()));
      return mv;
   }

   @GetMapping({"/nc/buscar"})
   public ModelAndView buscarNc(VentaFilter ventaFilter) {
      ModelAndView mv = new ModelAndView("notaCreditoVenta/buscarNotaCreditoVenta");
      mv.addObject(ventaFilter);
      mv.addObject("ventas", this.ventas.getVentaNc(ventaFilter));
      return mv;
   }

   @GetMapping({"/notaCreditoVentas/{id}"})
   public ModelAndView buscarNcById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Venta venta = (Venta)this.ventas.findById(id).orElse(null);
      this.agregarUUID(venta);

      for (ItemVenta item : venta.getItems()) {
         this.tablaItemVentaSession.adicionarItem(item.getProducto(), item.getPrecio(), item.getCosto(), item.getCantidad(), venta.getUuid());
      }

      return this.notaCredito(venta, usuarioSistema);
   }

   @GetMapping({"/buscarItem/por/id"})
   @ResponseBody
   public List<ItemVenta> getItemVenta(Venta venta) {
      return this.itemVentas.buscarPorVenta(venta);
   }

   @PostMapping({"/js/adicionar/por/venta"})
   public ModelAndView adicionarNcByVenta(Venta venta, String uuid) {
      for (ItemVenta item : this.itemVentas.buscarPorVenta(venta)) {
         this.tablaItemVentaSession.adicionarItem(item.getProducto(), item.getPrecio(), item.getCosto(), item.getCantidad().negate(), uuid);
      }

      ModelAndView mv = this.mvReturnNc(uuid);
      return mv;
   }

   @PostMapping({"/notaCreditoVentas"})
   public ModelAndView guardarNc(
      @Valid Venta venta, BindingResult result, @AuthenticationPrincipal UsuarioSistema usuarioSistema, RedirectAttributes attributes
   ) {
      ModelAndView mv = new ModelAndView("redirect:/ventas/notaCreditoVentas");
      venta.cargarItem(this.tablaItemVentaSession.getItems(venta.getUuid()));
      if (result.hasErrors()) {
         return this.notaCredito(venta, usuarioSistema);
      } else {
         try {
            this.ventaService.guardar(venta, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.notaCredito(venta, usuarioSistema);
         } catch (DataAccessException var8) {
            result.rejectValue("", "", var8.getMessage());
            return this.notaCredito(venta, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Nota de crédito guardada con exito! registro: " + venta.getId());
         return mv;
      }
   }

   @DeleteMapping({"/nc/elimiarItem"})
   public ModelAndView eliminarItemNc(int indice, String uuid) {
      this.tablaItemVentaSession.eliminarItemIndice(indice, uuid);
      return this.mvReturnNc(uuid);
   }

   @PostMapping({"/nc/adicionarItem"})
   public ModelAndView adicionarItemNc(
      Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Producto producto, BigDecimal precio, BigDecimal cantidad, String uuid
   ) {
      BigDecimal costo_cotizado = this.cotizaciones.fCotizar(producto.getMoneda().getId(), monedaDestino, fecha, producto.getCosto(), BigDecimal.ZERO);
      this.tablaItemVentaSession.adicionarItem(producto, precio, costo_cotizado, cantidad.negate(), uuid);
      return this.mvReturnNc(uuid);
   }

   @PutMapping({"/cambiar/precio/nc"})
   public ModelAndView cambiarPrecioNc(Long monedaDestino, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, Precio precio, String uuid) {
      for (ItemVenta item : this.tablaItemVentaSession.getItems(uuid)) {
         BigDecimal costo_cotizado = this.cotizaciones
            .fCotizar(item.getProducto().getMoneda().getId(), monedaDestino, fecha, item.getProducto().getCosto(), BigDecimal.ZERO);
         this.tablaItemVentaSession.modificarPrecioCosto(item.getProducto(), this.cambiarPrecioVenta(precio, item.getProducto()), costo_cotizado, uuid);
      }

      ModelAndView mv = this.mvReturnNc(uuid);
      return mv;
   }

   @PutMapping({"/item/modificar/cantidad/nc"})
   @ResponseBody
   public ModelAndView modificarCantidadNc(Producto producto, BigDecimal cantidad, String uuid) {
      this.tablaItemVentaSession.modificarCantidad(producto, cantidad.negate(), uuid);
      return this.mvReturnNc(uuid);
   }

   private ModelAndView mvReturnNc(String uuid) {
      ModelAndView mv = new ModelAndView("notaCreditoVenta/itemNotaCreditoVenta");
      mv.addObject("items", this.tablaItemVentaSession.getItems(uuid));
      mv.addObject("total", this.tablaItemVentaSession.totalPrecio(uuid));
      return mv;
   }

   @PostMapping({"/item-lote-adicionar"})
   @ResponseBody
   public ResponseEntity<?> adicionarItemLote(
      Producto producto,
      String nroLote,
      @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate vencimiento,
      BigDecimal cantidad,
      BigDecimal cantidadActual,
      String uuid
   ) {
      BigDecimal total = BigDecimal.ZERO;

      try {
         this.tablaItemVentaLoteSession.adicionarItem(producto, nroLote, vencimiento, cantidad, cantidadActual, uuid);
         total = this.tablaItemVentaLoteSession.getCantidadTotal(producto, uuid);
      } catch (Exception var9) {
         return ResponseEntity.badRequest().body(var9.getMessage());
      }

      return ResponseEntity.ok(total);
   }

   @GetMapping({"/get-items-lotes-by-producto"})
   public ModelAndView getItemsLoteByProducto(Producto producto, String uuid) {
      ModelAndView mv = new ModelAndView("venta/itemVentaLote");
      mv.addObject("itemsLotes", this.tablaItemVentaLoteSession.getItemsByProducto(producto, uuid));
      mv.addObject("totalCantidad", this.tablaItemVentaLoteSession.getCantidadTotal(producto, uuid));
      return mv;
   }

   @PutMapping({"/modificar-cantidad-lote"})
   @ResponseBody
   public ResponseEntity<?> mdodificarCantidadLote(Producto producto, String nroLote, BigDecimal cantidad, BigDecimal cantidadActual, String uuid) {
      BigDecimal total = BigDecimal.ZERO;

      try {
         this.tablaItemVentaLoteSession.modificarCantidad(producto, nroLote, cantidad, cantidadActual, uuid);
         total = this.tablaItemVentaLoteSession.getCantidadTotal(producto, uuid);
      } catch (Exception var8) {
         return ResponseEntity.badRequest().body(var8.getMessage());
      }

      return ResponseEntity.ok(total);
   }

   @DeleteMapping({"/eliminar-item-lote"})
   public ModelAndView eliinarItemLote(int indice, Producto producto, String uuid) {
      this.tablaItemVentaLoteSession.eliminarItem(indice, uuid);
      return this.getItemsLoteByProducto(producto, uuid);
   }

   @GetMapping({"/get-items-lotes-by-producto-detalle"})
   @ResponseBody
   public ResponseEntity<?> getLotesExisteDetalle(Producto producto, Deposito deposito, String uuid) {
      List<ItemVentaLote> items = this.tablaItemVentaLoteSession.getItemsByProducto(producto, uuid);

      for (ItemVentaLote item : items) {
         BigDecimal cantidadLote = this.lotes.getByLoteDepositoProducto(item.getNroLote(), item.getProducto().getId(), deposito).getCantidad();
         item.setCantidadActual(cantidadLote);
      }

      return ResponseEntity.ok(items);
   }

   @GetMapping({"/js/get-items-lotes-by-producto"})
   @ResponseBody
   public ResponseEntity<?> getItemsLotesByProductoSolo(Producto producto, String uuid) {
      new ArrayList();

      List items;
      try {
         items = this.tablaItemVentaLoteSession.getItemsByProducto(producto, uuid);
      } catch (Exception var5) {
         return ResponseEntity.badRequest().body(var5.getMessage());
      }

      return ResponseEntity.ok(items);
   }
}
