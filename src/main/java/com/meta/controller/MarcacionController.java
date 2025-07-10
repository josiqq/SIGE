package com.meta.controller;

import com.meta.modelo.Marcacion;
import com.meta.repository.Equipos;
import com.meta.service.MarcacionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/marcaciones"})
public class MarcacionController {
   @Autowired
   private Equipos equipos;
   @Autowired
   private MarcacionService marcacionService;

   @GetMapping
   public ModelAndView inicio(Marcacion marcacion) {
      ModelAndView mv = new ModelAndView("marcacion/marcacion");

      try {
         marcacion = this.marcacionService.buscarMarcacionAbierta();
      } catch (Exception var4) {
         marcacion = new Marcacion();
      }

      System.out.println("este es el maximo id=> " + this.marcacionService.getMaximoId());
      mv.addObject(marcacion);
      mv.addObject("equipos", this.equipos.findAll());
      return mv;
   }

   @PostMapping
   public ModelAndView guardar(@Valid Marcacion marcacion, BindingResult result) {
      if (result.hasErrors()) {
         return this.inicio(marcacion);
      } else {
         this.marcacionService.guardar(marcacion);
         return this.inicio(marcacion);
      }
   }

   @PostMapping({"/marcadorA"})
   @ResponseBody
   public String marcadorA(Integer puntoA) {
      this.marcacionService.modificarPuntoA(puntoA);
      return "ok modificado";
   }

   @PostMapping({"/marcadorB"})
   @ResponseBody
   public String marcadorB(Integer puntoB) {
      this.marcacionService.modificarPuntoB(puntoB);
      return "ok modificado";
   }

   @GetMapping({"/abiertas"})
   @ResponseBody
   public Marcacion abiertas() {
      new Marcacion();

      Marcacion marcacion;
      try {
         marcacion = this.marcacionService.buscarMarcacionAbierta();
      } catch (Exception var3) {
         marcacion = new Marcacion();
      }

      return marcacion;
   }

   @GetMapping({"/terminado"})
   @ResponseBody
   public Marcacion terminada() {
      Long id = this.marcacionService.getMaximoId();
      Marcacion marcacion = new Marcacion();
      if (id != null) {
         marcacion = this.marcacionService.getMarcacionTerminado(id);
      }

      return marcacion;
   }
}
