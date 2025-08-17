package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.dto.PlanillaImporteDTO;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPlanilla;
import com.sige.model.ItemPlanillaRendicion;
import com.sige.model.Moneda;
import com.sige.model.Planilla;
import com.sige.repository.Condiciones;
import com.sige.repository.Cotizaciones;
import com.sige.repository.Cuentas;
import com.sige.repository.Monedas;
import com.sige.repository.Planillas;
import com.sige.repository.filter.PlanillaFilter;
import com.sige.security.UsuarioSistema;
import com.sige.service.PlanillaService;
import com.sige.service.exeption.NegocioException;
import com.sige.session.planilla.TablaItemPlanillaRendicionSession;
import com.sige.session.planilla.TablaItemPlanillaSession;

@Controller
@RequestMapping({"/planillas"})
public class PlanillaController {
   @Autowired
   private Monedas monedas;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private PlanillaService planillaService;
   @Autowired
   private Planillas planillas;
   @Autowired
   private TablaItemPlanillaSession tablaItemPlanillaSession;
   @Autowired
   private TablaItemPlanillaRendicionSession tablaItemPlanillaRendicionSession;
   @Autowired
   private Condiciones condiciones;
   @Autowired
   private Cotizaciones cotizaciones;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Planilla planilla, @AuthenticationPrincipal UsuarioSistema usuariSistema) {
      ModelAndView mv = new ModelAndView("planilla/planilla");
      this.agregarUsurio(planilla, usuariSistema);
      this.agregarUUID(planilla);
      mv.addObject(planilla);
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("condiciones", this.condiciones.findAll());
      mv.addObject("cuentas", this.cuentas.getCuentaCobranza());
      mv.addObject("itemPlanillas", this.tablaItemPlanillaSession.getItemPlanillas(planilla.getUuid()));
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(
      @Valid Planilla planilla, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema
   ) {
      ModelAndView mv = new ModelAndView("redirect:/planillas");
      planilla.cargarItemPlanilla(this.tablaItemPlanillaSession.getItemPlanillas(planilla.getUuid()));
      if (result.hasErrors()) {
         return this.inicio(planilla, usuarioSistema);
      } else {
         try {
            this.planillaService.guardar(planilla);
         } catch (NegocioException var7) {
            result.rejectValue("", "", var7.getMessage());
            return this.inicio(planilla, usuarioSistema);
         }

         attributes.addFlashAttribute("mensaje", "Planilla guardado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(PlanillaFilter planillaFilter) {
      ModelAndView mv = new ModelAndView("planilla/buscarPlanilla");
      mv.addObject(planillaFilter);
      mv.addObject("planillas", this.planillaService.getPlanillas(planillaFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Planilla planilla = this.planillaService.getById(id);
      this.agregarUUID(planilla);

      for (ItemPlanilla item : planilla.getItemPlanillas()) {
         this.tablaItemPlanillaSession.adicionarItem(item.getCondicion(), item.getMoneda(), item.getImporte(), planilla.getUuid());
      }

      return this.inicio(planilla, usuarioSistema);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.planillaService.eliminar(id);
      } catch (PersistenceException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @PostMapping({"/adicionar/item"})
   public ModelAndView adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importe, String uuid) {
      this.tablaItemPlanillaSession.adicionarItem(condicion, moneda, importe, uuid);
      return this.getModelAndView(uuid);
   }

   @DeleteMapping({"/item/planilla/eliminar"})
   public ModelAndView eliminarItem(int indice, String uuid) {
      this.tablaItemPlanillaSession.eliminarItem(indice, uuid);
      return this.getModelAndView(uuid);
   }

   private ModelAndView getModelAndView(String uuid) {
      ModelAndView mv = new ModelAndView("planilla/itemPlanilla");
      mv.addObject("itemPlanillas", this.tablaItemPlanillaSession.getItemPlanillas(uuid));
      return mv;
   }

   private void agregarUsurio(Planilla planilla, UsuarioSistema usuariSistema) {
      if (planilla.getUsuario() == null) {
         planilla.setUsuario(usuariSistema.getUsuario());
      }
   }

   private void agregarUUID(Planilla planilla) {
      if (StringUtils.isEmpty(planilla.getUuid())) {
         planilla.setUuid(UUID.randomUUID().toString());
      }
   }

   @GetMapping({"/get/planilla/abierta"})
   @ResponseBody
   public Planilla getByCuenta(Cuenta cuenta) {
      Optional<Planilla> planillaOP = this.planillas.getByCuenta(cuenta);
      new Planilla();
      Planilla planilla;
      if (planillaOP.isPresent()) {
         planilla = planillaOP.get();
      } else {
         planilla = new Planilla();
      }

      return planilla;
   }

   @GetMapping({"/js/getPlanilla/byFecha"})
   @ResponseBody
   public List<Planilla> getPlanillasByFecha(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fecha) {
      return this.planillas.getPlanillasByFecha(fecha);
   }

   @GetMapping({"/cierre"})
   public ModelAndView cierre(Planilla planilla, @AuthenticationPrincipal UsuarioSistema usuario) {
      ModelAndView mv = new ModelAndView("planilla/cierrePlanilla");
      this.agregarUsurio(planilla, usuario);
      this.agregarUUID(planilla);
      mv.addObject(planilla);
      mv.addObject("planillas", this.planillas.getPlanillasAbiertas());
      mv.addObject("condiciones", this.condiciones.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("cuentas", this.cuentas.getCuentaNoCobranza());
      mv.addObject("itemsRendicion", this.tablaItemPlanillaRendicionSession.getItems(planilla.getUuid()));
      return mv;
   }

   @PostMapping({"/cierre"})
   public ModelAndView guardarCierrePlanilla(Planilla planilla, @AuthenticationPrincipal UsuarioSistema usuario, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/planillas/cierre");
      planilla.cargarItemPlanilla(this.tablaItemPlanillaSession.getItemPlanillas(planilla.getUuid()));
      planilla.cargarItemPlanillaRendicion(this.tablaItemPlanillaRendicionSession.getItems(planilla.getUuid()));
      this.planillaService.guardarPlanillaCierre(planilla);
      attributes.addFlashAttribute("registro", planilla.getId());
      attributes.addFlashAttribute("mensaje", "Cierre de planilla  guardado con éxito!");
      return mv;
   }

   @GetMapping({"/js/planilla/lista/importes"})
   @ResponseBody
   public List<PlanillaImporteDTO> getPlanillaImportes(Long planilla) {
      return this.planillas.getPlanillasImportes(planilla);
   }

   @PostMapping({"/js/adicionar/itemPlanillaRendicion"})
   public ModelAndView adicionarItemPlanillaRendicion(
      Condicion condicion, Moneda moneda, BigDecimal importe, Long vcuentaOrigen, Long vcuentaDestino, String uuid
   ) {
      Cuenta cuentaOrigen = (Cuenta)this.cuentas.findById(vcuentaOrigen).orElse(null);
      Cuenta cuentaDestino = (Cuenta)this.cuentas.findById(vcuentaDestino).orElse(null);
      Optional<Planilla> planillaOP = this.planillas.getByCuenta(cuentaOrigen);
      BigDecimal cotizacion = this.cotizaciones.fCotizacion(moneda.getId(), planillaOP.get().getCuenta().getId(), planillaOP.get().getFecha());
      ModelAndView mv = new ModelAndView("planilla/itemPlanillaRendicion");
      this.tablaItemPlanillaRendicionSession.adicionarItem(condicion, moneda, importe, cuentaOrigen, cuentaDestino, importe.multiply(cotizacion), uuid);
      mv.addObject("itemsRendicion", this.tablaItemPlanillaRendicionSession.getItems(uuid));
      return mv;
   }

   @DeleteMapping({"/js/eliminar/itemPlanillaRendicion"})
   public ModelAndView quitarItemPlanillaRendicion(int indice, String uuid) {
      ModelAndView mv = new ModelAndView("planilla/itemPlanillaRendicion");
      this.tablaItemPlanillaRendicionSession.eliminarItem(indice, uuid);
      mv.addObject("itemsRendicion", this.tablaItemPlanillaRendicionSession.getItems(uuid));
      return mv;
   }

   @PostMapping({"js/adicionarItemParaCierre"})
   @ResponseBody
   public String adicionarItemParaCierre(Long idPlanilla, String uuid) {
      if (idPlanilla != null) {
         Planilla planilla = (Planilla)this.planillas.findById(idPlanilla).orElse(null);

         for (ItemPlanilla item : planilla.getItemPlanillas()) {
            this.tablaItemPlanillaSession.adicionarItem(item.getCondicion(), item.getMoneda(), item.getImporte(), uuid);
         }
      }

      return "ok";
   }

   @GetMapping({"/cierre/buscar"})
   public ModelAndView buscarPlanillaRendicion() {
      ModelAndView mv = new ModelAndView("planilla/buscarPlanillaRendicion");
      mv.addObject("planillas", this.planillas.findAll());
      return mv;
   }

   @GetMapping({"/cierre/{id}"})
   public ModelAndView getCierreById(@PathVariable Long id, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      Planilla planilla = this.planillaService.getById(id);
      this.agregarUUID(planilla);

      for (ItemPlanillaRendicion item : planilla.getItemPlanillaRendiciones()) {
         this.tablaItemPlanillaRendicionSession
            .adicionarItem(
               item.getCondicion(), item.getMoneda(), item.getImporte(), item.getCuenta(), item.getCuentaDestino(), item.getImporteMn(), planilla.getUuid()
            );
      }

      for (ItemPlanilla item : planilla.getItemPlanillas()) {
         this.tablaItemPlanillaSession.adicionarItem(item.getCondicion(), item.getMoneda(), item.getImporte(), planilla.getUuid());
      }

      return this.cierre(planilla, usuarioSistema);
   }

   @GetMapping({"/js/getItemPlanillaRendicion"})
   @ResponseBody
   public List<ItemPlanillaRendicion> getItemPlanillaRendicion(Long idPlanilla) {
      return this.planillas.getItemPlanillaRendiciones(idPlanilla);
   }
}
