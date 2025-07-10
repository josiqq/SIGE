package com.meta.controller;

import com.meta.dto.InventarioDTO;
import com.meta.dto.StockValorizadoDTO;
import com.meta.repository.Depositos;
import com.meta.repository.Marcas;
import com.meta.repository.Precios;
import com.meta.repository.Productos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
