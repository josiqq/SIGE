package com.sige.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.SeguimientoCostos;
import com.sige.repository.filter.SeguimientoCostoFilter;

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
