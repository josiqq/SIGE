package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.AjustePrecios;
import com.sige.repository.Precios;
import com.sige.repository.filter.AjustePrecioFilter;

@Controller
@RequestMapping({"/reportePrecio"})
public class ReportePrecioController {
   @Autowired
   private Precios precios;
   @Autowired
   private AjustePrecios ajustes;

   @GetMapping({"/preciosMobile"})
   public ModelAndView precioMobile() {
      ModelAndView mv = new ModelAndView("reporte/precio/listaPrecioMobile");
      mv.addObject("precios", this.precios.findAll());
      return mv;
   }

   @GetMapping({"/js/getPreciosMobile"})
   @ResponseBody
   public List<Object[]> getPreciosMobile(Long precio) {
      return this.precios.getPreciosMobile(precio);
   }

   @GetMapping({"/buscar"})
   public ModelAndView buscar(AjustePrecioFilter filter) {
      ModelAndView mv = new ModelAndView("reporte/precio/ajustePrecio");
      mv.addObject("precios", this.precios.findAll());
      mv.addObject("items", this.ajustes.buscarItemAjustePrecio(filter));
      return mv;
   }
}
