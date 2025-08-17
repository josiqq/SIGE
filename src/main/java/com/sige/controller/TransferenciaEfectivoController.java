package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.config.BigDecimalEditor;
import com.sige.config.IntegerEditor;
import com.sige.model.Parametro;
import com.sige.model.ParametroVenta;
import com.sige.model.TransferenciaEfectivo;
import com.sige.repository.Condiciones;
import com.sige.repository.Cuentas;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Parametros;
import com.sige.repository.filter.TransferenciaEfectivoFilter;
import com.sige.service.TransferenciaEfectivoService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/transferenciaEfectivos"})
public class TransferenciaEfectivoController {
   @Autowired
   private TransferenciaEfectivoService transferenciaEfectivoService;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private Condiciones condiciones;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private ParametroVentas parametroVentas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(TransferenciaEfectivo transferenciaEfectivo) {
      ModelAndView mv = new ModelAndView("transferenciaEfectivo/transferenciaEfectivo");
      mv.addObject(transferenciaEfectivo);
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("condiciones", this.condiciones.findAll());
      this.agregarDatosParametrizados(transferenciaEfectivo);
      return mv;
   }

   private void agregarDatosParametrizados(TransferenciaEfectivo transferenciaEfectivo) {
      Parametro parametro = this.parametros.getParametro();
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      if (transferenciaEfectivo.getMoneda() == null) {
         transferenciaEfectivo.setMoneda(parametro.getMoneda());
      }

      if (transferenciaEfectivo.getCondicion() == null) {
         transferenciaEfectivo.setCondicion(parametroVenta.getCondicion());
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid TransferenciaEfectivo TransferenciaEfectivo, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/transferenciaEfectivos");
      if (result.hasErrors()) {
         return this.inicio(TransferenciaEfectivo);
      } else {
         try {
            this.transferenciaEfectivoService.guardar(TransferenciaEfectivo);
         } catch (NegocioException var6) {
            result.rejectValue("importe", var6.getMessage(), var6.getMessage());
            return this.inicio(TransferenciaEfectivo);
         }

         attributes.addFlashAttribute("mensaje", "Transferencia realizada con éxisto!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarTransferencia(TransferenciaEfectivoFilter transferenciaEfectivoFilter) {
      ModelAndView mv = new ModelAndView("transferenciaEfectivo/buscarTransferenciaEfectivo");
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("transferenciaEfectivos", this.transferenciaEfectivoService.buscarTransferencia(transferenciaEfectivoFilter));
      mv.addObject("totalImporte", this.transferenciaEfectivoService.totalImporte(transferenciaEfectivoFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      TransferenciaEfectivo transferenciaEfectivo = this.transferenciaEfectivoService.buscarPorId(id);
      transferenciaEfectivo.setImporte(transferenciaEfectivo.getImporte().setScale(0, RoundingMode.HALF_UP));
      return this.inicio(transferenciaEfectivo);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.transferenciaEfectivoService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
