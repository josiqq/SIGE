package com.sige.controller;

import java.math.BigDecimal;
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
import com.sige.model.Gasto;
import com.sige.model.Parametro;
import com.sige.model.ParametroVenta;
import com.sige.repository.Condiciones;
import com.sige.repository.Cuentas;
import com.sige.repository.Monedas;
import com.sige.repository.ParametroVentas;
import com.sige.repository.Parametros;
import com.sige.repository.filter.GastoFilter;
import com.sige.service.GastoService;
import com.sige.service.exeption.ImposibleEliminarExeption;

@Controller
@RequestMapping({"/gastos"})
public class GastoController {
   @Autowired
   private GastoService gastoService;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private Monedas monedas;
   @Autowired
   private Parametros parametros;
   @Autowired
   private Condiciones condiciones;
   @Autowired
   private ParametroVentas parametroVentas;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Gasto gasto) {
      ModelAndView mv = new ModelAndView("gasto/gasto");
      mv.addObject(gasto);
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("condiciones", this.condiciones.findAll());
      this.agregarDatosParametrizados(gasto);
      return mv;
   }

   private void agregarDatosParametrizados(Gasto gasto) {
      Parametro parametro = this.parametros.getParametro();
      ParametroVenta parametroVenta = this.parametroVentas.buscarParametroVenta();
      if (gasto.getMoneda() == null) {
         gasto.setMoneda(parametro.getMoneda());
      }

      System.out.println("condicion: " + parametroVenta.getCondicion().getNombre());
      if (gasto.getCondicion() == null) {
         gasto.setCondicion(parametroVenta.getCondicion());
      }
   }

   @PostMapping
   public ModelAndView guardar(@Valid Gasto gasto, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/gastos");
      if (result.hasErrors()) {
         return this.inicio(gasto);
      } else {
         try {
            this.gastoService.guardar(gasto);
         } catch (Exception var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(gasto);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se guardaron con exito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(GastoFilter gastoFilter) {
      ModelAndView mv = new ModelAndView("gasto/buscarGasto");
      mv.addObject(gastoFilter);
      mv.addObject("gastos", this.gastoService.buscarGasto(gastoFilter));
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject("totalImporte", this.gastoService.totalImporte(gastoFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      System.out.println("importe: " + this.gastoService.buscarPorId(id).getImporte());
      return this.inicio(this.gastoService.buscarPorId(id));
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
      try {
         this.gastoService.eliminar(id);
      } catch (ImposibleEliminarExeption var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
