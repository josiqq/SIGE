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

import com.sige.model.Equipo;
import com.sige.service.EquipoService;
import com.sige.service.exeption.NegocioException;

@Controller
@RequestMapping({"/equipos"})
public class EquipoController {
   @Autowired
   private EquipoService equipoService;

   @GetMapping
   public ModelAndView inicio(Equipo equipo) {
      ModelAndView mv = new ModelAndView("equipo/equipo");
      mv.addObject(equipo);
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Equipo equipo, BindingResult result, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/equipos");
      if (result.hasErrors()) {
         return this.inicio(equipo);
      } else {
         this.equipoService.guardar(equipo);
         attributes.addFlashAttribute("mensaje", "Equipo guardado con éxito!");
         return mv;
      }
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(Equipo equipo) {
      ModelAndView mv = new ModelAndView("equipo/buscarEquipo");
      mv.addObject(equipo);
      mv.addObject("equipos", this.equipoService.buscarPorNombre(equipo));
      return mv;
   }

   @GetMapping({"/{id}"})
   public ModelAndView buscarPorId(@PathVariable Long id) {
      Equipo equipo = this.equipoService.buscarPorId(id);
      return this.inicio(equipo);
   }

   @DeleteMapping({"/{id}"})
   @ResponseBody
   public ResponseEntity<?> eliminar(@PathVariable("id") Equipo equipo) {
      try {
         this.equipoService.eliminar(equipo);
      } catch (NegocioException var3) {
         return ResponseEntity.badRequest().body(var3.getMessage());
      }

      return ResponseEntity.ok().build();
   }

   @GetMapping({"/buscarFoto"})
   @ResponseBody
   public String buscarFoto(Long id) {
      System.out.println("llegó pio aqui para buscar la foto ?" + id);
      String nada = "";

      try {
         return this.equipoService.buscarFoto(id);
      } catch (Exception var4) {
         return nada;
      }
   }
}
