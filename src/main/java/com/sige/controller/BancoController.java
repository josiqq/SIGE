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

import com.sige.model.Banco;
import com.sige.repository.Bancos;
import com.sige.service.BancoService;

@Controller
@RequestMapping({"/bancos"})
public class BancoController {
   @Autowired
   private BancoService bancoService;
   @Autowired
   private Bancos bancos;

   @GetMapping
   public ModelAndView inicio(Banco banco) {
      ModelAndView mv = new ModelAndView("banco/banco");
      mv.addObject(banco);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Banco banco, BindingResult result, RedirectAttributes attribute) {
      ModelAndView mv = new ModelAndView("redirect:/bancos");
      if (result.hasErrors()) {
         return this.inicio(banco);
      } else {
         try {
            this.bancoService.guardar(banco);
            attribute.addFlashAttribute("mensaje", "Banco guardado con exito!");
            return mv;
         } catch (Exception var6) {
            result.rejectValue("", "", var6.getMessage());
            return this.inicio(banco);
         }
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar() {
      ModelAndView mv = new ModelAndView("banco/buscarBanco");
      mv.addObject("bancos", this.bancos.findAll());
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView getById(@PathVariable Long id) {
      Banco banco = (Banco)this.bancos.findById(id).orElse(null);
      return this.inicio(banco);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.bancoService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/js/allBancos"})
   @ResponseBody
   public List<Banco> getAllBancosJs() {
      return this.bancos.findAll();
   }
}
