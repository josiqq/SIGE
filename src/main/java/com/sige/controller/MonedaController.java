package com.sige.controller;

import java.util.List;
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

import com.sige.model.Moneda;
import com.sige.service.MonedaService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/monedas"})
public class MonedaController {
   @Autowired
   private MonedaService monedaService;

   @GetMapping
   public ModelAndView inicio(Moneda moneda) {
      ModelAndView mv = new ModelAndView("moneda/moneda");
      mv.addObject(moneda);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Moneda moneda, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/monedas");
      if (result.hasErrors()) {
         return this.inicio(moneda);
      } else {
         try {
            this.monedaService.guardar(moneda);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(moneda);
         }

         attributes.addFlashAttribute("mensaje", "Modeda registrada correctamente!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView getMonedas() {
      ModelAndView mv = new ModelAndView("moneda/buscarMoneda");
      mv.addObject("monedas", this.monedaService.getAllMonedas());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id) {
      Moneda moneda = this.monedaService.getById(id);
      return this.inicio(moneda);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.monedaService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/monedas"})
   @ResponseBody
   public List<Moneda> getAllMonedas() {
      return this.monedaService.getAllMonedas();
   }
}
