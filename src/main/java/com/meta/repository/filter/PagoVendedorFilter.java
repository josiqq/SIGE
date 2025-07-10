package com.meta.repository.filter;

import com.meta.modelo.Cuenta;
import com.meta.modelo.Vendedor;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class PagoVendedorFilter {
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private Vendedor vendedor;
   private Cuenta cuenta;

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

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }
}
