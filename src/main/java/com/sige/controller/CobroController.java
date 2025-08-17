package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.Banco;
import com.sige.model.Cobro;
import com.sige.model.ComisionTarjeta;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemCobro;
import com.sige.model.ItemCobroImporte;
import com.sige.model.Moneda;
import com.sige.model.Parametro;
import com.sige.model.ParametroVenta;
import com.sige.model.Usuario;
import com.sige.model.Venta;
import com.sige.repository.Bancos;
import com.sige.repository.Cobros;
import com.sige.repository.Condiciones;
import com.sige.repository.Cuentas;
import com.sige.repository.ItemCobros;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Parametros;
import com.sige.repository.Usuarios;
import com.sige.repository.filter.CobroFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.CobroService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.cobro.TablaItemCobroImporteSession;
import com.sige.session.cobro.TablaItemCobroSession;

@Controller
@RequestMapping({"/cobros"})
public class CobroController {
   @Autowired
   private TablaItemCobroSession tablaItemCobroSession;
   @Autowired
   private TablaItemCobroImporteSession tablaItemCobroImporteSession;
   @Autowired
   private Usuarios usuarios;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private CobroService cobroService;
   @Autowired
   private ItemCobros itemCobros;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Condiciones condiciones;
   @Autowired
   private ParametroVentas parametroVentas;
   @Autowired
   private Bancos bancos;
   @Autowired
   private Parametros parametros;
   @Autowired
   private Cobros cobros;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Cobro cobro, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("cobro/cobro");
      this.cargarUsuario(cobro, usuarioSistema);
      cobro.setTotal(this.quitarDecimal(cobro.getTotal()));
      mv.addObject(cobro);
      this.agregarUUID(cobro);
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(cobro.getUuid()));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(cobro.getUuid()));
      mv.addObject("getItemImportes", this.tablaItemCobroImporteSession.getItems(cobro.getUuid()));
      mv.addObject("getTotalImporteImportems", this.tablaItemCobroImporteSession.getTotalImportems(cobro.getUuid()));
      mv.addObject("condiciones", this.condiciones.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("bancos", this.bancos.findAll());
      this.agregarParametros(cobro);
      return mv;
   }

   private void agregarParametros(Cobro cobro) {
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      Parametro parametro = this.parametros.getParametro();
      if (cobro.getCondicion() == null) {
         cobro.setCondicion(parametroVenta.getCondicion());
      }

      if (cobro.getMoneda() == null) {
         cobro.setMoneda(parametro.getMoneda());
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid Cobro cobro, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      cobro.setTotal(this.tablaItemCobroImporteSession.getTotalImportems(cobro.getUuid()));
      System.out.println("total cobro: " + cobro.getTotal());
      ModelAndView mv = new ModelAndView("redirect:/cobros");
      cobro.cargarItemCobro(this.tablaItemCobroSession.getItems(cobro.getUuid()));
      cobro.cargarItemCobroImporte(this.tablaItemCobroImporteSession.getItems(cobro.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(cobro, usuarioSistema);
      } else {
         try {
            this.cobroService.guardarPrincipal(cobro, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(cobro, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Cobro nro: " + cobro.getId() + " guardado con éxito!");
         attributes.addFlashAttribute("registro", cobro.getId());
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarCobro(CobroFilter cobroFilter) {
      ModelAndView mv = new ModelAndView("cobro/buscarCobro");
      mv.addObject("cobros", this.cobroService.getCobros(cobroFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Cobro cobro = this.cobroService.getById(id);
      this.agregarUUID(cobro);

      for (ItemCobro itemCobro : cobro.getItemsCobros()) {
         this.tablaItemCobroSession.adicionarItem(itemCobro.getVenta(), itemCobro.getFecha(), itemCobro.getImporte(), cobro.getUuid());
      }

      for (ItemCobroImporte itemCobroImporte : cobro.getItemsCobrosImportes()) {
         this.tablaItemCobroImporteSession
            .adicionarItemPrincipal(
               itemCobroImporte.getCuenta(),
               itemCobroImporte.getCondicion(),
               itemCobroImporte.getMoneda(),
               itemCobroImporte.getValorCotizacion(),
               this.quitarDecimal(itemCobroImporte.getImporte()),
               itemCobroImporte.getImportems(),
               this.quitarDecimal(itemCobroImporte.getImporteCobrado()),
               itemCobroImporte.getMonedaVuelto(),
               itemCobroImporte.getCotizacionVuelto(),
               itemCobroImporte.getVuelto(),
               itemCobroImporte.getVueltoms(),
               this.verificarBanco(itemCobroImporte.getBanco()),
               itemCobroImporte.getBoleta(),
               cobro.getUuid()
            );
      }

      return this.inicio(cobro, usuarioSistema);
   }

   private Banco verificarBanco(Banco banco) {
      Banco bancoRetorno = new Banco();
      if (banco != null) {
         bancoRetorno = banco;
      }

      return bancoRetorno;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.cobroService.eliminar(id, usuarioSistema);
      } catch (NegocioException var4) {
         ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/js/item/cobro"})
   @ResponseBody
   public ResponseEntity<?> adicionarItemCobroPrincipal(Venta venta, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, BigDecimal importe, String uuid) {
      this.tablaItemCobroSession.adicionarItem(venta, fecha, importe, uuid);
      return ResponseEntity.ok().build();
   }

   @PostMapping({"/js/adicionar/item/con/venta"})
   public ModelAndView adicionarItemConVenta(Venta venta, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, BigDecimal importe, String uuid) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroPrincipal");
      this.tablaItemCobroSession.adicionarItem(venta, fecha, importe, uuid);
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @PostMapping({"/js/adicionar/con/comision/tarjeta"})
   public ModelAndView adicionarItemComisionTarjeta(
      ComisionTarjeta comisionTarjeta, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, BigDecimal importe, String uuid
   ) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroPrincipal");
      this.tablaItemCobroSession.adicionarItemComisionTarjeta(comisionTarjeta, fecha, importe, uuid);
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @GetMapping({"/js/item/getCobro"})
   public ModelAndView getItemCobro(String uuid) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroPrincipal");
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @DeleteMapping({"/js/item/eliminar"})
   @ResponseBody
   public ResponseEntity<?> eliminarItemPrincipal(Venta venta, String uuid) {
      this.tablaItemCobroSession.eliminarItemConVenta(venta, uuid);
      return ResponseEntity.ok().build();
   }

   @PutMapping({"/js/item/modificar"})
   public ModelAndView modificarItemPrincipal(Venta venta, BigDecimal importe, String uuid) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroPrincipal");
      this.tablaItemCobroSession.modificarItem(venta, importe, uuid);
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @DeleteMapping({"/js/item/eliminar/retorno"})
   public ModelAndView eliminarItemRetorno(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroPrincipal");
      this.tablaItemCobroSession.elimiarItem(indice, uuid);
      mv.addObject("itemCobros", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @PostMapping({"/js/adicionar/item/importe"})
   public ModelAndView adicionarItemImportePrincipal(
      Cuenta cuenta,
      Condicion condicion,
      Moneda moneda,
      BigDecimal valorContizacion,
      BigDecimal importe,
      BigDecimal importems,
      BigDecimal cobrado,
      Long monv,
      BigDecimal cotizacionVuelto,
      BigDecimal vuelto,
      BigDecimal vueltoms,
      Banco banco,
      String boleta,
      String uuid
   ) {
      Moneda monedaVuelto = (Moneda)this.monedas.findById(monv).orElse(null);
      ModelAndView mv = new ModelAndView("cobro/itemCobroImportePrincipal");
      this.tablaItemCobroImporteSession
         .adicionarItemPrincipal(
            cuenta,
            condicion,
            moneda,
            this.conDecimalCotizacion(moneda, valorContizacion),
            this.conDecimal(moneda, importe),
            importems,
            this.conDecimal(moneda, cobrado),
            monedaVuelto,
            this.conDecimalCotizacion(monedaVuelto, cotizacionVuelto),
            this.conDecimal(monedaVuelto, vuelto),
            vueltoms,
            banco,
            boleta,
            uuid
         );
      mv.addObject("getItemImportes", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("getTotalImporteImportems", this.tablaItemCobroImporteSession.getTotalImportems(uuid));
      return mv;
   }

   @DeleteMapping({"/js/eliminar/item/importe"})
   public ModelAndView eliminarItemImportePrincipal(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("cobro/itemCobroImportePrincipal");
      this.tablaItemCobroImporteSession.eliminarItemImportePrincipal(indice, uuid);
      mv.addObject("getItemImportes", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("getTotalImporteImportems", this.tablaItemCobroImporteSession.getTotalImportems(uuid));
      return mv;
   }

   @GetMapping({"/js/buscar/item/cobro"})
   @ResponseBody
   public List<ItemCobro> getItemCobroByCobro(Cobro cobro) {
      return this.itemCobros.getItemCobroByCobro(cobro);
   }

   private BigDecimal quitarDecimal(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }

   private BigDecimal agregarDecimal(BigDecimal valor) {
      return valor.setScale(16, RoundingMode.HALF_UP);
   }

   private void agregarUUID(Cobro cobro) {
      if (StringUtils.isEmpty(cobro.getUuid())) {
         cobro.setUuid(UUID.randomUUID().toString());
      }
   }

   private void cargarUsuario(Cobro cobro, UsuarioSistema usuarioSistema) {
      if (cobro.getUsuario() == null) {
         Usuario usuario = this.usuarios.buscarUsuario(usuarioSistema.getUsername());
         cobro.setUsuario(usuario);
         cobro.setCajero(usuario.getCajero());
         cobro.setCuenta(usuario.getCuenta());
      }
   }

   @PostMapping({"/js/adicionar/item/importe/mb"})
   public ModelAndView adicionarItemImportemb(
      Cuenta cuenta, BigDecimal importe, BigDecimal importeCobrado, BigDecimal vuelto, Condicion condicion, Moneda moneda, Long monedaVuelto, String uuid
   ) {
      ModelAndView mv = new ModelAndView("venta/ventaMobile/item.cobro.importe.venta.mobile");
      Moneda monedaVue = (Moneda)this.monedas.findById(monedaVuelto).orElse(null);
      BigDecimal newImporteCobrado;
      BigDecimal newImporte;
      if (moneda.getDecimales() == 0) {
         newImporteCobrado = this.quitarDecimal(importeCobrado);
         newImporte = this.quitarDecimal(importe);
      } else {
         newImporteCobrado = this.agregarDecimal(importeCobrado);
         newImporte = this.agregarDecimal(importe);
      }

      BigDecimal newVuelto;
      if (monedaVue.getDecimales() == 0) {
         newVuelto = this.quitarDecimal(vuelto);
      } else {
         newVuelto = this.agregarDecimal(vuelto);
      }

      this.tablaItemCobroImporteSession.adicionarItemVentaMB(cuenta, newImporte, newImporteCobrado, newVuelto, condicion, moneda, monedaVue, uuid);
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      mv.addObject("getItemImportes", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("getTotalImporteImporte", this.tablaItemCobroImporteSession.getTotalTotal(uuid));
      return mv;
   }

   @DeleteMapping({"/js/eliminar/item/importe/mb"})
   public ModelAndView eliminarMb(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("venta/ventaMobile/item.cobro.importe.venta.mobile");
      this.tablaItemCobroImporteSession.eliminarItemImportePrincipal(indice, uuid);
      mv.addObject("getItemImportes", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("getTotalImporteImporte", this.tablaItemCobroImporteSession.getTotalTotal(uuid));
      return mv;
   }

   @PostMapping({"/item/cobro"})
   @ResponseBody
   public ModelAndView adicionarItem(Venta venta, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha, BigDecimal importe, String uuid) {
      this.tablaItemCobroSession.adicionarItem(venta, fecha, this.quitarDecimal(importe), uuid);
      ModelAndView mv = new ModelAndView("cobro/itemCobro");
      mv.addObject("items", this.tablaItemCobroSession.getItems(uuid));
      mv.addObject("totalCobro", this.tablaItemCobroSession.getTotal(uuid));
      return mv;
   }

   @PostMapping({"/item/importe"})
   @ResponseBody
   public ModelAndView adicionarItemImporte(
      Cuenta cuenta,
      Condicion condicion,
      Moneda moneda,
      BigDecimal valorContizacion,
      BigDecimal importe,
      BigDecimal importems,
      BigDecimal cobrado,
      Long monv,
      BigDecimal cotizacionVuelto,
      BigDecimal vuelto,
      BigDecimal vueltoms,
      Banco banco,
      String boleta,
      String uuid
   ) {
      Moneda monedaVuelto = (Moneda)this.monedas.findById(monv).orElse(null);
      this.tablaItemCobroImporteSession
         .adicionarItemPrincipal(
            cuenta,
            condicion,
            moneda,
            this.conDecimalCotizacion(moneda, valorContizacion),
            this.conDecimal(moneda, importe),
            importems,
            this.conDecimal(moneda, cobrado),
            monedaVuelto,
            this.conDecimalCotizacion(monedaVuelto, cotizacionVuelto),
            this.conDecimal(monedaVuelto, vuelto),
            vueltoms,
            banco,
            boleta,
            uuid
         );
      ModelAndView mv = new ModelAndView("venta/cobro/item.cobro.importe.venta");
      mv.addObject("itemCobros", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("totalImporteMs", this.quitarDecimal(this.tablaItemCobroImporteSession.getTotalImportems(uuid)));
      return mv;
   }

   @DeleteMapping({"/item/importe/eliminar"})
   public ModelAndView eliminarItemDesdeVenta(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("venta/cobro/item.cobro.importe.venta");
      this.tablaItemCobroImporteSession.eliminarItemImportePrincipal(indice, uuid);
      mv.addObject("itemCobros", this.tablaItemCobroImporteSession.getItems(uuid));
      mv.addObject("totalImporteMs", this.quitarDecimal(this.tablaItemCobroImporteSession.getTotalImportems(uuid)));
      return mv;
   }

   @PostMapping({"/venta"})
   @ResponseBody
   public ResponseEntity<?> guardarDesdeVenta(@RequestBody Cobro cobro, BindingResult result, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      cobro.cargarItemCobro(this.tablaItemCobroSession.getItems(cobro.getUuid()));
      cobro.cargarCliente();
      cobro.cargarItemCobroImporte(this.tablaItemCobroImporteSession.getItems(cobro.getUuid()));
      this.cobroService.guardar(cobro, usuarioSistema);
      return ResponseEntity.ok().build();
   }

   private BigDecimal conDecimal(Moneda moneda, BigDecimal importe) {
      BigDecimal retorno = BigDecimal.ZERO;
      if (importe.compareTo(BigDecimal.ZERO) == 0) {
         retorno = BigDecimal.ZERO;
      } else {
         retorno = importe.setScale(16, RoundingMode.HALF_UP);
      }

      return retorno;
   }

   private BigDecimal conDecimalCotizacion(Moneda moneda, BigDecimal importe) {
      BigDecimal retorno = BigDecimal.ZERO;
      if (importe.compareTo(BigDecimal.ZERO) == 0) {
         retorno = BigDecimal.ZERO;
      } else {
         retorno = importe.setScale(2, RoundingMode.HALF_UP);
      }

      return retorno;
   }

   @GetMapping({"/js/itemCobroImporte/get"})
   @ResponseBody
   public List<ItemCobroImporte> getItemCobroImporte(Long id) {
      return this.cobros.getItemCobroImporte(id);
   }
}
