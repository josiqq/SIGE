package com.meta.controller;

import com.meta.dto.CuentaPorPagarDTO;
import com.meta.repository.CuentaPorPagars;
import com.meta.repository.filter.CuentaPorPagarFilter;
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
@RequestMapping({"/reportes/cuentasPorPagar"})
public class ReporteCuentaPorPagarController {
   @Autowired
   private CuentaPorPagars cuentaPorPagars;

   @GetMapping({"/cuentas"})
   public ModelAndView inicio(CuentaPorPagarFilter cuentaPorPagarFilter) {
      ModelAndView mv = new ModelAndView("reporte/cuentaPorPagar/cuentaPorPagarPendiente");
      mv.addObject(cuentaPorPagarFilter);
      return mv;
   }

   @PostMapping({"/pendientes"})
   @ResponseBody
   public List<CuentaPorPagarDTO> getCuentaPedientes(@RequestBody CuentaPorPagarFilter cuentaPorPagarFilter) {
      return this.cuentaPorPagars.getCuetasPorPagarDTO(cuentaPorPagarFilter);
   }
}
