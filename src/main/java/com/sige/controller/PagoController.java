package com.sige.controller;

import java.math.BigDecimal;
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
import com.sige.model.Compra;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPago;
import com.sige.model.ItemPagoImporte;
import com.sige.model.Moneda;
import com.sige.model.Pago;
import com.sige.model.Parametro;
import com.sige.repository.Condiciones;
import com.sige.repository.Cuentas;
import com.sige.repository.Monedas;
import com.sige.repository.Parametros;
import com.sige.repository.filter.PagoFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.PagoService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.pago.TablaItemPagoImporteSession;
import com.sige.session.pago.TablaItemPagoSession;

@Controller
@RequestMapping({"/pagos"})
public class PagoController {
   @Autowired
   private TablaItemPagoSession tablaItemPagoSession;
   @Autowired
   private TablaItemPagoImporteSession tablaItemPagoImporteSession;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private PagoService pagoService;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private Condiciones condiciones;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Pago pago) {
      ModelAndView mv = new ModelAndView("pago/pago");
      mv.addObject(pago);
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      this.agregarUUID(pago);
      pago.setTotal(pago.getTotal());
      pago.setSaldo(pago.getSaldo());
      mv.addObject("items", this.tablaItemPagoSession.getItems(pago.getUuid()));
      mv.addObject("total", this.tablaItemPagoSession.getTotalImporte(pago.getUuid()));
      mv.addObject("itemsImporte", this.tablaItemPagoImporteSession.getItems(pago.getUuid()));
      mv.addObject("totalImporte", this.tablaItemPagoImporteSession.getTotalImporte(pago.getUuid()));
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("condiciones", this.condiciones.findAll());
      this.agregarDatosParametrizados(pago);
      return mv;
   }

   private void agregarDatosParametrizados(Pago pago) {
      Parametro parametro = this.parametros.getParametro();
      if (pago.getMoneda() == null) {
         pago.setMoneda(parametro.getMoneda());
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid Pago pago, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("redirect:/pagos");
      if (result.hasErrors()) {
         return this.inicio(pago);
      } else {
         pago.cargarPagoItem(this.tablaItemPagoSession.getItems(pago.getUuid()));
         pago.cargarPagoItemImporte(this.tablaItemPagoImporteSession.getItems(pago.getUuid()));

         try {
            this.pagoService.guardar(pago, usuarioSistema);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(pago);
         }

         attributes.addFlashAttribute("mensaje", "Pago nro: " + pago.getId() + " guardado con exito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView getPagos(PagoFilter pagoFilter) {
      ModelAndView mv = new ModelAndView("pago/buscarPago");
      mv.addObject(pagoFilter);
      mv.addObject("pagos", this.pagoService.getPagos(pagoFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getOne(@PathVariable Long id) {
      Pago pago = this.pagoService.getOne(id);
      this.agregarUUID(pago);

      for (ItemPago itemPago : pago.getItemPagos()) {
         this.tablaItemPagoSession.adicionarItem(itemPago.getCompra(), itemPago.getImporte(), pago.getUuid());
      }

      for (ItemPagoImporte itemPagoImporte : pago.getItemPagoImportes()) {
         this.tablaItemPagoImporteSession
            .adicionarItem(
               itemPagoImporte.getCondicion(),
               itemPagoImporte.getMoneda(),
               itemPagoImporte.getImporteMs(),
               itemPagoImporte.getCuenta(),
               itemPagoImporte.getImporte(),
               pago.getUuid()
            );
      }

      return this.inicio(pago);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> delPago(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      try {
         this.pagoService.elimina(id, usuarioSistema);
      } catch (NegocioException var4) {
         return ResponseEntity.badRequest().body(var4.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   private void agregarUUID(Pago pago) {
      if (StringUtils.isEmpty(pago.getUuid())) {
         pago.setUuid(UUID.randomUUID().toString());
      }
   }

   @PostMapping({"/item/adicionar"})
   @ResponseBody
   public String adicionarItem(Compra compra, BigDecimal importe, String uuid) {
      this.tablaItemPagoSession.adicionarItem(compra, importe, uuid);
      return "ok";
   }

   @GetMapping({"/item/getItems"})
   @ResponseBody
   public ModelAndView getItems(String uuid) {
      ModelAndView mv = new ModelAndView("pago/itemPago");
      mv.addObject("items", this.tablaItemPagoSession.getItems(uuid));
      mv.addObject("total", this.tablaItemPagoSession.getTotalImporte(uuid));
      return mv;
   }

   @DeleteMapping({"/item/eliminar"})
   @ResponseBody
   public String eliminarItem(Compra compra, String uuid) {
      this.tablaItemPagoSession.eliminarItem(compra, uuid);
      return "ok";
   }

   @DeleteMapping({"/item/eliminar/retorno"})
   @ResponseBody
   public ModelAndView eliminarItemRetorno(Compra compra, String uuid) {
      this.tablaItemPagoSession.eliminarItem(compra, uuid);
      ModelAndView mv = new ModelAndView("pago/itemPago");
      mv.addObject("items", this.tablaItemPagoSession.getItems(uuid));
      mv.addObject("total", this.tablaItemPagoSession.getTotalImporte(uuid));
      return mv;
   }

   @PutMapping({"/item/modificar"})
   @ResponseBody
   public ModelAndView modificarItem(Compra compra, BigDecimal importe, String uuid) {
      ModelAndView mv = new ModelAndView("pago/itemPago");
      this.tablaItemPagoSession.modificarItem(compra, importe, uuid);
      mv.addObject("items", this.tablaItemPagoSession.getItems(uuid));
      mv.addObject("total", this.tablaItemPagoSession.getTotalImporte(uuid));
      return mv;
   }

   @PostMapping({"/item/importe/adicionar"})
   @ResponseBody
   public ModelAndView adcionarImporte(Condicion condicion, Moneda moneda, BigDecimal importeMs, Cuenta cuenta, BigDecimal importe, String uuid) {
      ModelAndView mv = new ModelAndView("pago/itemPagoImporte");
      this.tablaItemPagoImporteSession.adicionarItem(condicion, moneda, importeMs, cuenta, importe, uuid);
      mv.addObject("itemsImporte", this.tablaItemPagoImporteSession.getItems(uuid));
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      mv.addObject("totalImporte", this.tablaItemPagoImporteSession.getTotalImporte(uuid));
      return mv;
   }

   @PutMapping({"/item/importe/modificar"})
   @ResponseBody
   public ModelAndView modificarImporte(int indice, Cuenta cuenta, BigDecimal importe, String uuid) {
      ModelAndView mv = new ModelAndView("pago/itemPagoImporte");
      this.tablaItemPagoImporteSession.modificarItem(indice, cuenta, importe, uuid);
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      mv.addObject("itemsImporte", this.tablaItemPagoImporteSession.getItems(uuid));
      mv.addObject("totalImporte", this.tablaItemPagoImporteSession.getTotalImporte(uuid));
      return mv;
   }

   @DeleteMapping({"/item/importe/eliminar"})
   @ResponseBody
   public ModelAndView eliminarImporte(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("pago/itemPagoImporte");
      this.tablaItemPagoImporteSession.eliminarItem(indice, uuid);
      mv.addObject("cuentas", this.cuentas.buscarCuentasActivas());
      mv.addObject("itemsImporte", this.tablaItemPagoImporteSession.getItems(uuid));
      mv.addObject("totalImporte", this.tablaItemPagoImporteSession.getTotalImporte(uuid));
      return mv;
   }

   private void agregarSaldoImporte(Pago pago) {
      if (pago.getTotal().compareTo(BigDecimal.ZERO) > 0 && pago.getId() != null) {
         pago.setPagado(this.tablaItemPagoImporteSession.getTotalImporte(pago.getUuid()));
         pago.setSaldo(pago.getTotal().subtract(pago.getPagado()));
      }
   }
}
