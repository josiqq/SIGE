package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.dto.InventarioDTO;
import com.sige.dto.StockValorizadoDTO;
import com.sige.repository.Depositos;
import com.sige.repository.Marcas;
import com.sige.repository.Precios;
import com.sige.repository.Productos;

@Controller
@RequestMapping({"/reporteStock"})
public class ReporteStockController {
   @Autowired
   private Productos productos;
   @Autowired
   private Depositos depositos;
   @Autowired
   private Marcas marcas;
   @Autowired
   private Precios precios;

   @GetMapping({"/inventario"})
   public ModelAndView nventario() {
      ModelAndView mv = new ModelAndView("reporte/stock/inventario");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("marcas", this.marcas.findAll());
      return mv;
   }

   @GetMapping({"/js/getInventario"})
   @ResponseBody
   public List<InventarioDTO> getInventario(Long deposito, Long marca) {
      return this.productos.getInventario(deposito, marca);
   }

   @GetMapping({"/stockValorizado"})
   public ModelAndView stockValorizado(Long deposito, Long precio) {
      ModelAndView mv = new ModelAndView("reporte/stock/stockValorizado");
      mv.addObject("depositos", this.depositos.buscarDepositoActivo());
      mv.addObject("precios", this.precios.findAll());
      return mv;
   }

   @GetMapping({"/js/stockValorizado"})
   @ResponseBody
   public List<StockValorizadoDTO> getStockValorizado(Long deposito, Long precio) {
      return this.productos.getStockValorizado(deposito, precio);
   }
}
