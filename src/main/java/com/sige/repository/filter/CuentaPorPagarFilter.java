package com.sige.repository.filter;

import java.math.BigInteger;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class CuentaPorPagarFilter {
   private BigInteger idCompra;
   private BigInteger idProveedor;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimientoDesde;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimientoHasta;

   public BigInteger getIdCompra() {
      return this.idCompra;
   }

   public void setIdCompra(BigInteger idCompra) {
      this.idCompra = idCompra;
   }

   public BigInteger getIdProveedor() {
      return this.idProveedor;
   }

   public void setIdProveedor(BigInteger idProveedor) {
      this.idProveedor = idProveedor;
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

   public LocalDate getVencimientoDesde() {
      return this.vencimientoDesde;
   }

   public void setVencimientoDesde(LocalDate vencimientoDesde) {
      this.vencimientoDesde = vencimientoDesde;
   }

   public LocalDate getVencimientoHasta() {
      return this.vencimientoHasta;
   }

   public void setVencimientoHasta(LocalDate vencimientoHasta) {
      this.vencimientoHasta = vencimientoHasta;
   }
}
