package com.sige.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.dto.ItemVentaPorVendedorComisionDTO;
import com.sige.repository.ItemVentas;
import com.sige.repository.Vendedores;
import com.sige.repository.Ventas;
import com.sige.repository.filter.VentaFilter;

@Controller
@RequestMapping({"/reporteVenta"})
public class ReporteVentaController {
   @Autowired
   private Ventas ventas;
   @Autowired
   private ItemVentas itemVentas;
   @Autowired
   private Vendedores vendedores;

   @GetMapping({"/ventaPorProductos"})
   public ModelAndView getVentaPorProducto(VentaFilter ventaFilter) {
      ModelAndView mv = new ModelAndView("reporte/venta/ventaPorProducto");
      mv.addObject(ventaFilter);
      mv.addObject("ventas", this.ventas.getVentaPorProducto(ventaFilter));
      List<Object[]> listaVenta = this.ventas.getVentaPorProducto(ventaFilter);
      BigDecimal totalCosto = BigDecimal.ZERO;
      BigDecimal totalTotal = BigDecimal.ZERO;
      BigDecimal totalUtilidad = BigDecimal.ZERO;
      totalCosto = listaVenta.stream().map(obj -> (BigDecimal)obj[4]).reduce(BigDecimal.ZERO, BigDecimal::add);
      totalTotal = listaVenta.stream().map(obj -> (BigDecimal)obj[5]).reduce(BigDecimal.ZERO, BigDecimal::add);
      totalUtilidad = listaVenta.stream().map(obj -> (BigDecimal)obj[6]).reduce(BigDecimal.ZERO, BigDecimal::add);
      mv.addObject("totalCosto", totalCosto);
      mv.addObject("totalTotal", totalTotal);
      mv.addObject("totalUtilidad", totalUtilidad);
      return mv;
   }

   @GetMapping({"/venta/vendedor/comision"})
   public ModelAndView ventaPorVendedorComision() {
      ModelAndView mv = new ModelAndView("reporte/venta/venta.por.vendedor.comision");
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      return mv;
   }

   @GetMapping({"/js/ventaPorProductos"})
   @ResponseBody
   public List<Object[]> getVentaPorProductoJs(VentaFilter ventaFilter) {
      return this.ventas.getVentaPorProducto(ventaFilter);
   }

   @GetMapping({"/js/ventaPorVendedorComision"})
   @ResponseBody
   public List<ItemVentaPorVendedorComisionDTO> getVentaPorVendedorComision(
      Long vendedor, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta
   ) {
      return this.itemVentas.gentVentaByVendedorComision(vendedor, fechaDesde, fechaHasta);
   }
}
