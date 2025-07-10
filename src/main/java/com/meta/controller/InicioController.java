package com.meta.controller;

import com.meta.repository.Cotizaciones;
import com.meta.repository.Gastos;
import com.meta.repository.Membresias;
import com.meta.repository.Parametros;
import com.meta.service.ClienteService;
import com.meta.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/"})
public class InicioController {
   @Autowired
   private ClienteService clienteService;
   @Autowired
   private Gastos gastos;
   @Autowired
   private Membresias membresias;
   @Autowired
   private CotizacionService cotizacionService;
   @Autowired
   private Cotizaciones cotizaciones;
   @Autowired
   private Parametros parametros;

   @GetMapping
   public ModelAndView inicio() {
      ModelAndView mv = new ModelAndView("inicio");
      mv.addObject("cantidadClientes", this.clienteService.cantidadCliente());
      mv.addObject("gastoDia", this.gastos.gastoDelDia());
      mv.addObject("memebreDia", this.membresias.membresiaDelDia());
      this.cotizacionService.verificarCotizacion();
      return mv;
   }
}
