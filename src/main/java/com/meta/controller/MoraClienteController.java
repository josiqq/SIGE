package com.meta.controller;

import com.meta.repository.MoraClientes;
import com.meta.repository.filter.MoraClienteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/moras"})
public class MoraClienteController {
   @Autowired
   private MoraClientes moraClientes;

   @GetMapping
   public ModelAndView buscar(MoraClienteFilter moraClienteFilter) {
      ModelAndView mv = new ModelAndView("clientes/moraCliente");
      mv.addObject("moraClientes", this.moraClientes.buscarNombreDocumento(moraClienteFilter));
      return mv;
   }
}
