package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.Cobros;

@Controller
@RequestMapping({"/reporteCobro"})
public class ReporteCobroController {
   @Autowired
   private Cobros cobros;

   @GetMapping({"/arqueoCaja"})
   public ModelAndView arqueoCaja() {
      return new ModelAndView("reporte/cobro/arqueo.caja");
   }

   @GetMapping({"/js/getArqueo"})
   @ResponseBody
   public List<Object[]> getArqueo(String sql, Long planilla) {
      return this.cobros.getArqueoCaja(sql, planilla);
   }

   @GetMapping({"/cobros-realizados"})
   public ModelAndView cobrosRealizados() {
      return new ModelAndView("reporte/cobro/cobros.realizados");
   }

   @GetMapping({"/get-cobros-realizados"})
   @ResponseBody
   public List<Object[]> getCobrosRealizados(String sql) {
      return this.cobros.getCobrosRealizadosMoneda(sql);
   }
}
