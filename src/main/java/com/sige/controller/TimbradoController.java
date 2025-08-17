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
import com.sige.model.Timbrado;
import com.sige.service.TimbradoService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/timbrados"})
public class TimbradoController {
   @Autowired
   private TimbradoService timbradoService;

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditor());
      binder.registerCustomEditor(Integer.class, new IntegerEditor());
   }

   @GetMapping
   public ModelAndView inicio(Timbrado timbrado) {
      ModelAndView mv = new ModelAndView("timbrado/timbrado");
      mv.addObject(timbrado);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Timbrado timbrado, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/timbrados");
      if (result.hasErrors()) {
         return this.inicio(timbrado);
      } else {
         try {
            this.timbradoService.guardar(timbrado);
         } catch (NegocioException var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(timbrado);
         }

         attributes.addFlashAttribute("mensaje", "Timbrado nro: " + timbrado.getId() + " guardado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView getTimbrados(Timbrado timbrado) {
      ModelAndView mv = new ModelAndView("timbrado/buscarTimbrado");
      mv.addObject(timbrado);
      mv.addObject("timbrados", this.timbradoService.getTimbrados(timbrado));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id) {
      Timbrado timbrado = this.timbradoService.getById(id);
      return this.inicio(timbrado);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.timbradoService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
