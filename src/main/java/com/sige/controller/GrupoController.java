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

import com.sige.model.Grupo;
import com.sige.repository.Permisos;
import com.sige.service.GrupoService;

@Controller
@RequestMapping({"/grupos"})
public class GrupoController {
   @Autowired
   private Permisos permisos;
   @Autowired
   private GrupoService grupoService;

   @GetMapping
   public ModelAndView inicio(Grupo grupo) {
      ModelAndView mv = new ModelAndView("grupo/grupo");
      mv.addObject("permisos", this.permisos.findAll());
      mv.addObject(grupo);
      return mv;
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Grupo grupo) {
      ModelAndView mv = new ModelAndView("grupo/buscarGrupo");
      mv.addObject("grupos", this.grupoService.buscarPorNombre(grupo));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Grupo grupo = this.grupoService.buscarPorId(id);
      return this.inicio(grupo);
   }

   @PostMapping
   public ModelAndView guardar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/grupos");
      if (result.hasErrors()) {
         return this.inicio(grupo);
      } else {
         try {
            this.grupoService.guardar(grupo);
         } catch (Exception var6) {
            result.rejectValue("nombre", var6.getMessage(), var6.getMessage());
            return this.inicio(grupo);
         }

         attributes.addFlashAttribute("mensaje", "Grupo guardado con exito!");
         return mv;
      }
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
      try {
         this.grupoService.eliminar(id);
      } catch (Exception var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }
}
