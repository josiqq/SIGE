package com.meta.controller;

import com.meta.repository.Instructores;
import com.meta.repository.MovInstructores;
import com.meta.repository.filter.MovInstructorFilter;
import com.meta.security.UsuarioSistema;
import com.meta.service.MovInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/movInstructores"})
public class MovInstructorController {
   @Autowired
   private MovInstructores movInstructores;
   @Autowired
   private Instructores instructores;
   @Autowired
   private MovInstructorService movInstructorService;

   @GetMapping
   public ModelAndView inicio(MovInstructorFilter movInstructorFilter, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
      ModelAndView mv = new ModelAndView("movInstructor/movInstructor");
      String nombreUsuario = usuarioSistema.getUsername();
      mv.addObject("movInstructores", this.movInstructorService.buscarMovimiento(movInstructorFilter, nombreUsuario));
      mv.addObject("totalMovimiento", this.movInstructorService.totalesMov(movInstructorFilter, nombreUsuario));
      mv.addObject("instructores", this.instructores.findAll());
      return mv;
   }
}
