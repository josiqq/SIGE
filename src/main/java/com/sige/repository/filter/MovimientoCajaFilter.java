package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Cuenta;

public class MovimientoCajaFilter {
   private Cuenta cuenta;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public LocalDate getFechaDesde() {
      return this.fechaDesde;
   }

   public void setFechaDesde(LocalDate fechaDesde) {
      this.fechaDesde = fechaDesde;
   }

   public LocalDate getFechaHasta() {
      return this.fechaHasta;
   }

   public void setFechaHasta(LocalDate fechaHasta) {
      this.fechaHasta = fechaHasta;
   }
}
