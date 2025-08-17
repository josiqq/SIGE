package com.sige.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.Cotizaciones;
import com.sige.repository.Gastos;
import com.sige.repository.Membresias;
import com.sige.repository.Parametros;
import com.sige.service.ClienteService;
import com.sige.service.CotizacionService;

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
