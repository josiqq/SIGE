package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.dto.CuentaPorCobrarDTO;
import com.sige.model.CondicionVenta;
import com.sige.repository.CuentaPorCobrars;
import com.sige.repository.filter.CuentaPorCobrarFilter;

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
