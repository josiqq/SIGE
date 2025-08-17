package com.sige.repository.filter;

import java.math.BigInteger;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.CondicionVenta;

public class CuentaPorCobrarFilter {
   private BigInteger idVenta;
   private BigInteger idCliente;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private CondicionVenta condicionVenta;

   public BigInteger getIdVenta() {
      return this.idVenta;
   }

   public void setIdVenta(BigInteger idVenta) {
      this.idVenta = idVenta;
   }

   public BigInteger getIdCliente() {
      return this.idCliente;
   }

   public void setIdCliente(BigInteger idCliente) {
      this.idCliente = idCliente;
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

   public CondicionVenta getCondicionVenta() {
      return this.condicionVenta;
   }

   public void setCondicionVenta(CondicionVenta condicionVenta) {
      this.condicionVenta = condicionVenta;
   }
}
