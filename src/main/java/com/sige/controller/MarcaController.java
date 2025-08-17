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

import com.sige.model.Marca;
import com.sige.service.MarcaService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/marcas"})
public class MarcaController {
   @Autowired
   private MarcaService marcaService;

   @GetMapping
   public ModelAndView inicio(Marca marca) {
      ModelAndView mv = new ModelAndView("marca/marca");
      mv.addObject(marca);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Marca marca, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/marcas");
      if (result.hasErrors()) {
         return this.inicio(marca);
      } else {
         try {
            this.marcaService.guardar(marca);
         } catch (NegocioException var6) {
            result.rejectValue("nombre", var6.getMessage(), var6.getMessage());
            return this.inicio(marca);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se guardaron con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Marca marca) {
      ModelAndView mv = new ModelAndView("marca/buscarMarca");
      mv.addObject("marcas", this.marcaService.buscarMarca(marca));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Marca marca = this.marcaService.buscarPorId(id);
      return this.inicio(marca);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.marcaService.eliminar(id);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
