package com.meta.controller;

import com.meta.modelo.Tabla;
import com.meta.modelo.Tipo;
import com.meta.repository.Auditorias;
import com.meta.repository.Usuarios;
import com.meta.repository.filter.AuditoriaFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
