package com.sige.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/exhibirMarcaciones"})
public class ExhibirMarcacionController {
   @GetMapping
   public ModelAndView inicio() {
      return new ModelAndView("marcacion/exhibirMarcacion");
   }
}
