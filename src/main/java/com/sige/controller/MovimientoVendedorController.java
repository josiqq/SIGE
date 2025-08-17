package com.sige.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sige.model.MovimientoVendedor;
import com.sige.model.Vendedor;
import com.sige.repository.MovimientoVendedores;
import com.sige.repository.Vendedores;

@RequestMapping({"/movimiento-vendedor"})
@Controller
public class MovimientoVendedorController {
   @Autowired
   private Vendedores vendedores;
   @Autowired
   private MovimientoVendedores movimientoVendedores;

   @GetMapping({"/extracto"})
   public ModelAndView inicio() {
      ModelAndView mv = new ModelAndView("movimientoVendedor/movimientoVendedor");
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      return mv;
   }

   @GetMapping({"/get"})
   @ResponseBody
   public List<MovimientoVendedor> getMovimiento(
      Vendedor vendedor, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaDesde, @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fechaHasta
   ) {
      return this.movimientoVendedores.getMovimiento(vendedor, fechaDesde, fechaHasta);
   }

   @GetMapping({"/recalcular/movimiento"})
   public ModelAndView recalcularMovimiento() {
      ModelAndView mv = new ModelAndView("movimientoVendedor/recalcular.movimiento.vendedor");
      mv.addObject("vendedores", this.vendedores.buscarVendedoresAactivos());
      return mv;
   }

   @GetMapping({"/recalcular"})
   @ResponseBody
   public ResponseEntity<?> recalcular(Long id) {
      try {
         this.movimientoVendedores.recalcularMovimiento(id);
      } catch (DataAccessException var5) {
         Throwable rootCause = var5.getRootCause();
         String errorMessage = "Error en la base de datos: " + rootCause.getMessage();
         return ResponseEntity.badRequest().body(errorMessage);
      }

      return ResponseEntity.ok().build();
   }
}
