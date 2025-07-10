package com.meta.controller;

import com.meta.dto.CuentaPorCobrarDTO;
import com.meta.modelo.CondicionVenta;
import com.meta.repository.CuentaPorCobrars;
import com.meta.repository.filter.CuentaPorCobrarFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/reporte/cuentas"})
public class ReporteCuentaPorCobrarController {
   @Autowired
   private CuentaPorCobrars cuentaPorCobrars;

   @GetMapping({"/cuentasPorCobrar"})
   public ModelAndView getCuetasPorCobrar(CuentaPorCobrarFilter cuentaPorCobrarFilter) {
      ModelAndView mv = new ModelAndView("reporte/cuentaPorCobrar/cuentaPorCobrarPendiente");
      mv.addObject(cuentaPorCobrarFilter);
      cuentaPorCobrarFilter.setCondicionVenta(CondicionVenta.CREDITO);
      mv.addObject("condicionVentas", CondicionVenta.values());
      return mv;
   }

   @PostMapping({"/pendientes"})
   @ResponseBody
   public List<CuentaPorCobrarDTO> getCuentaPedientes(@RequestBody CuentaPorCobrarFilter cuentaPorCobrarFilter) {
      return this.cuentaPorCobrars.getCuentaPorCobrarDTO(cuentaPorCobrarFilter);
   }
}
