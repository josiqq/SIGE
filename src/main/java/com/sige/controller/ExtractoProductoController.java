package com.sige.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sige.model.ExtractoProducto;
import com.sige.repository.Depositos;
import com.sige.repository.ExtractoProductos;
import com.sige.repository.filter.ExtractoProductoFilter;

@Controller
@RequestMapping({"/extractoProducto"})
public class ExtractoProductoController {
   @Autowired
   private ExtractoProductos extractoProductos;
   @Autowired
   private Depositos depositos;

   @GetMapping
   public ModelAndView inicio(ExtractoProductoFilter extractoProductoFilter) {
      ModelAndView mv = new ModelAndView("extractoProducto/extractoProducto");
      mv.addObject("extractoProductos", this.extractoProductos.buscarExtracto(extractoProductoFilter));
      BigDecimal totalEntrada = this.extractoProductos
         .buscarExtracto(extractoProductoFilter)
         .stream()
         .map(ExtractoProducto::getEntrada)
         .reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal totalSalida = this.extractoProductos
         .buscarExtracto(extractoProductoFilter)
         .stream()
         .map(ExtractoProducto::getSalida)
         .reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal diferencia = totalEntrada.subtract(totalSalida);
      mv.addObject("totalEntrada", totalEntrada);
      mv.addObject("totalSalida", totalSalida);
      mv.addObject("diferencia", diferencia);
      mv.addObject("depositos", this.depositos.findAll());
      return mv;
   }

   public BigDecimal quitarDecimal(BigDecimal valor) {
      return valor.setScale(0, RoundingMode.HALF_UP);
   }
}
