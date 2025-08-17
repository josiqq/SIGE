package com.sige.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.Cajero;
import com.sige.repository.filter.CajeroFilter;
import com.sige.service.CajeroService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/cajeros"})
public class CajeroController {
   @Autowired
   private CajeroService cajeroService;

   @GetMapping
   public ModelAndView inicio(Cajero cajero) {
      ModelAndView mv = new ModelAndView("cajero/cajero");
      mv.addObject(cajero);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Cajero cajero, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/cajeros");
      if (result.hasErrors()) {
         return this.inicio(cajero);
      } else {
         try {
            this.cajeroService.guardar(cajero);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(cajero);
         }

         attributes.addFlashAttribute("mensaje", "Cajero rgistrado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(CajeroFilter cajeroFilter) {
      ModelAndView mv = new ModelAndView("cajero/buscarCajero");
      mv.addObject("cajeros", this.cajeroService.buscarPorNombreDocumento(cajeroFilter));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Cajero cajero = this.cajeroService.buscarPorId(id);
      return this.inicio(cajero);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.cajeroService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
