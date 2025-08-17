package com.sige.controller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sige.repository.Cuentas;
import com.sige.repository.MovimientoCajas;
import com.sige.repository.filter.MovimientoCajaFilter;

@Controller
@RequestMapping({"/movimientoCajas"})
public class MovimientoCajaController {
   @Autowired
   private MovimientoCajas movimientoCajas;
   @Autowired
   private Cuentas cuentas;

   @GetMapping
   public ModelAndView inicio(MovimientoCajaFilter movimientoCajaFilter) {
      ModelAndView mv = new ModelAndView("cuentas/movimientoCuenta");
      BigDecimal totalCredito = BigDecimal.ZERO;
      BigDecimal saldo = BigDecimal.ZERO;
      BigDecimal totalDebito = BigDecimal.ZERO;
      BigDecimal debitoAnterior = this.movimientoCajas.debitoAnterior(movimientoCajaFilter);
      totalCredito = this.movimientoCajas.creditoAnterior(movimientoCajaFilter).add(this.movimientoCajas.totalCredito(movimientoCajaFilter));
      totalDebito = debitoAnterior.add(this.movimientoCajas.totalDebito(movimientoCajaFilter));
      saldo = totalCredito.subtract(totalDebito);
      mv.addObject(movimientoCajaFilter);
      mv.addObject("movimientoCajas", this.movimientoCajas.buscarMovimiento(movimientoCajaFilter));
      mv.addObject("cuentas", this.cuentas.findAll());
      mv.addObject(
         "creditoAnterior",
         this.movimientoCajas.creditoAnterior(movimientoCajaFilter) != null ? this.movimientoCajas.creditoAnterior(movimientoCajaFilter) : BigDecimal.ZERO
      );
      mv.addObject("debitoAnterior", this.movimientoCajas.debitoAnterior(movimientoCajaFilter));
      mv.addObject(
         "totalCredito",
         this.movimientoCajas.totalCredito(movimientoCajaFilter) != null ? this.movimientoCajas.totalCredito(movimientoCajaFilter) : BigDecimal.ZERO
      );
      mv.addObject(
         "totalDebito",
         this.movimientoCajas.totalDebito(movimientoCajaFilter) != null ? this.movimientoCajas.totalDebito(movimientoCajaFilter) : BigDecimal.ZERO
      );
      mv.addObject("saldo", saldo);
      return mv;
   }
}
