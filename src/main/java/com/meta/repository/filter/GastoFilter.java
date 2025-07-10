package com.meta.repository.filter;

import com.meta.modelo.Moneda;
import com.meta.modelo.Proveedor;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class GastoFilter {
   private Proveedor proveedor;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private Moneda moneda;

   public Proveedor getProveedor() {
      return this.proveedor;
   }

   public void setProveedor(Proveedor proveedor) {
      this.proveedor = proveedor;
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

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }
}
