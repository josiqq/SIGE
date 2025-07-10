package com.meta.controller;

import com.meta.repository.SeguimientoCostos;
import com.meta.repository.filter.SeguimientoCostoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/seguimientoCostos"})
public class SeguimientoCostoController {
   @Autowired
   private SeguimientoCostos seguimientoCostos;

   @GetMapping
   public ModelAndView inicio(SeguimientoCostoFilter seguimientoCostoFilter) {
      ModelAndView mv = new ModelAndView("seguimientoCosto/seguimientoCosto");
      mv.addObject("seguimientoCostos", this.seguimientoCostos.buscarSeguimieto(seguimientoCostoFilter));
      return mv;
   }
}
