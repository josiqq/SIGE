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

import com.sige.model.Deposito;
import com.sige.service.DepositoService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/depositos"})
public class DepositoController {
   @Autowired
   private DepositoService depositoService;

   @GetMapping
   public ModelAndView inicio(Deposito deposito) {
      ModelAndView mv = new ModelAndView("deposito/deposito");
      mv.addObject(deposito);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Deposito deposito, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/depositos");
      if (result.hasErrors()) {
         return this.inicio(deposito);
      } else {
         try {
            this.depositoService.guardar(deposito);
         } catch (NegocioException var6) {
            result.rejectValue("nombre", var6.getMessage(), var6.getMessage());
            return this.inicio(deposito);
         }

         attributes.addFlashAttribute("mensaje", "Los datos se guardaron con éxito!");
         return mv;
      }
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Deposito deposito = this.depositoService.buscarPorId(id);
      return this.inicio(deposito);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Deposito deposito) {
      ModelAndView mv = new ModelAndView("deposito/buscarDeposito");
      mv.addObject("depositos", this.depositoService.buscarDeposito(deposito));
      return mv;
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.depositoService.eliminar(id);
      } catch (NegocioException var3) {
         ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscar/{id}"})
   @ResponseBody
   public Deposito retornoId(@PathVariable Long id) {
      return this.depositoService.buscarPorId(id);
   }
}
