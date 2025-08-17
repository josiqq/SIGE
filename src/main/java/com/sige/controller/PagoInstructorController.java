package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.sige.model.PagoInstructor;
import com.sige.repository.Cuentas;
import com.sige.repository.Instructores;
import com.sige.repository.filter.PagoInstructorFilter;
import com.sige.service.PagoInstructorService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/pagoInstructores"})
public class PagoInstructorController {
   @Autowired
   private PagoInstructorService pagoInstructorService;
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private Instructores instructores;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(PagoInstructor pagoInstructor) {
      ModelAndView mv = new ModelAndView("pagoInstructor/pagoInstructor");
      LocalDate fecha = LocalDate.now();
      pagoInstructor.setFecha(fecha);
      mv.addObject(pagoInstructor);
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject("instructores", this.instructores.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid PagoInstructor pagoInstructor, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/pagoInstructores");
      if (result.hasErrors()) {
         return this.inicio(pagoInstructor);
      } else {
         this.pagoInstructorService.guardar(pagoInstructor);
         attributes.addFlashAttribute("mensaje", "Pago se registró con exito");
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      PagoInstructor pagoInstructor = this.pagoInstructorService.buscarPorId(id);
      return this.inicio(pagoInstructor);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscarPago(PagoInstructorFilter pagoInstructorFilter) {
      ModelAndView mv = new ModelAndView("pagoInstructor/buscarPagoInstructor");
      mv.addObject("pagoInstructores", this.pagoInstructorService.buscarPago(pagoInstructorFilter));
      mv.addObject("totalImporte", this.pagoInstructorService.totalImporte(pagoInstructorFilter));
      mv.addObject("instructores", this.instructores.findAll());
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.pagoInstructorService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
