package com.sige.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sige.model.Tabla;
import com.sige.model.Tipo;
import com.sige.repository.Auditorias;
import com.sige.repository.Usuarios;
import com.sige.repository.filter.AuditoriaFilter;

@Controller
@RequestMapping({"/auditorias"})
public class AuditoriaController {
   @Autowired
   private Auditorias auditorias;
   @Autowired
   private Usuarios usuarios;

   @GetMapping
   public ModelAndView inicio(AuditoriaFilter filter) {
      ModelAndView mv = new ModelAndView("auditoria/auditoria");
      mv.addObject("tablas", Tabla.values());
      mv.addObject("tipos", Tipo.values());
      mv.addObject("usuarios", this.usuarios.findAll());
      mv.addObject("auditorias", this.auditorias.getAuditoriasByFilter(filter));
      return mv;
   }
}
