package com.sige.controller;

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sige.model.Parametro;
import com.sige.repository.Monedas;
import com.sige.repository.Parametros;
import com.sige.service.ParametroService;

@Controller
@RequestMapping({"/parametros"})
public class ParametroController {
   @Autowired
   private Parametros parametros;
   @Autowired
   private ParametroService parametroService;
   @Autowired
   private Monedas monedas;

   @GetMapping({"/{id}"})
   @CacheEvict(
      value = {"parametros"},
      allEntries = true
   )
   public ModelAndView inicio(@PathVariable Long id) {
      ModelAndView mv = new ModelAndView("parametro/parametro");
      Long cantParametro = this.parametros.cantParametro(id);
      if (cantParametro == 0L) {
         this.parametroService.esNuevo();
      }

      Parametro parametro = (Parametro)this.parametros.findById(id).orElse(null);
      mv.addObject("monedas", this.monedas.findAll());
      mv.addObject(parametro);
      return mv;
   }

   @PostMapping
   @CacheEvict(
      value = {"parametros"},
      allEntries = true
   )
   public ModelAndView guardar(Parametro parametro, RedirectAttributes attributes) {
      ModelAndView mv = new ModelAndView("redirect:/parametros/1");
      this.parametroService.guardar(parametro);
      attributes.addFlashAttribute("mensaje", "Parametro se ha guardado con exito");
      return mv;
   }

   @Cacheable({"parametros"})
   @RequestMapping({"/buscar/{id}"})
   @ResponseBody
   public Parametro buscar(@PathVariable Long id) {
      LocalTime hoy = LocalTime.now();
      System.out.println("select en parametros---" + hoy);
      return (Parametro)this.parametros.findById(id).orElse(null);
   }

   @GetMapping({"/js/get/parametros"})
   @ResponseBody
   public Parametro getParametro() {
      return this.parametros.getParametro();
   }
}
