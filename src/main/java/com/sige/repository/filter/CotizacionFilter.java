package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Moneda;

public class CotizacionFilter {
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private Moneda monedaOrigen;
   private Moneda monedaDestino;

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

   public Moneda getMonedaOrigen() {
      return this.monedaOrigen;
   }

   public void setMonedaOrigen(Moneda monedaOrigen) {
      this.monedaOrigen = monedaOrigen;
   }

   public Moneda getMonedaDestino() {
      return this.monedaDestino;
   }

   public void setMonedaDestino(Moneda monedaDestino) {
      this.monedaDestino = monedaDestino;
   }
}
