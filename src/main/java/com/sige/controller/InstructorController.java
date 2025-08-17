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
import com.sige.model.Instructor;
import com.sige.repository.filter.InstructorFilter;
import com.sige.service.InstructorService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/instructores"})
public class InstructorController {
   @Autowired
   private InstructorService instructorService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Instructor instructor) {
      ModelAndView mv = new ModelAndView("instructor/instructor");
      mv.addObject(instructor);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Instructor instructor, BindingResult result, RedirectAttributes attributes) {
      System.out.println("saldo instructor--" + instructor.getSaldo());
      ModelAndView mv = new ModelAndView("redirect:/instructores");
      if (result.hasErrors()) {
         return this.inicio(instructor);
      } else {
         try {
            this.instructorService.guardar(instructor);
         } catch (NegocioException var6) {
            result.rejectValue("documento", var6.getMessage(), var6.getMessage());
            return this.inicio(instructor);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se guardaron con exito!");
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Instructor instructor = this.instructorService.buscarPorId(id);
      return this.inicio(instructor);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(InstructorFilter instructorFilter) {
      ModelAndView mv = new ModelAndView("instructor/buscarInstructor");
      mv.addObject("instructores", this.instructorService.buscarNombreDocumento(instructorFilter));
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.instructorService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscar/{id}"})
   @ResponseBody
   public BigDecimal buscarSaldo(@PathVariable Long id) {
      System.out.println("id de instructor---" + id);
      return this.instructorService.saldoInstructor(id);
   }
}
