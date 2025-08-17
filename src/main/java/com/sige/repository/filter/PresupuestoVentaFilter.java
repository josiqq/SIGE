package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class PresupuestoVentaFilter {
   public Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaDesde;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaHasta;
   public Integer limite = 10;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public Integer getLimite() {
      return this.limite;
   }

   public void setLimite(Integer limite) {
      this.limite = limite;
   }
}
