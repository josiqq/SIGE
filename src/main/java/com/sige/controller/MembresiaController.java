package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.sige.model.Membresia;
import com.sige.repository.Cuentas;
import com.sige.repository.filter.MembresiaFilter;
import com.sige.service.InstructorService;
import com.sige.service.MembresiaService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/membresias"})
public class MembresiaController {
   @Autowired
   private Cuentas cuentas;
   @Autowired
   private MembresiaService membresiaService;
   @Autowired
   private InstructorService instructorService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Membresia membresia) {
      ModelAndView mv = new ModelAndView("membresia/membresia");
      mv.addObject(membresia);
      mv.addObject("instructores", this.instructorService.buscarTodos());
      mv.addObject("cuentas", this.cuentas.findAll());
      return mv;
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(MembresiaFilter membresiaFilter) {
      ModelAndView mv = new ModelAndView("membresia/buscarMembresia");
      mv.addObject(membresiaFilter);
      mv.addObject("membresias", this.membresiaService.buscar(membresiaFilter));
      mv.addObject("totalImporte", this.membresiaService.totalImporte(membresiaFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      return this.inicio(this.membresiaService.buscarPorId(id));
   }

   @PostMapping
   public ModelAndView guardar(@Valid Membresia membresia, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/membresias");
      if (result.hasErrors()) {
         return this.inicio(membresia);
      } else {
         this.membresiaService.guardar(membresia);
         attributes.addFlashAttribute("mensaje", "Los datos se han guardado con exito!");
         return mv;
      }
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
      try {
         this.membresiaService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscarFecha/{id}"})
   @ResponseBody
   public String buscarFecha(@PathVariable Long id) {
      LocalDate fechaNueva;
      if (this.membresiaService.ultimaFecha(id) == null) {
         fechaNueva = LocalDate.now();
      } else {
         fechaNueva = this.membresiaService.ultimaFecha(id).plusMonths(1L);
      }

      return fechaNueva.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   }
}
