package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaPorCobrarDTO {
   private String fecha;
   private String cliente;
   private BigInteger venta;
   private BigDecimal importe;
   private BigDecimal importeCobrado;
   private BigDecimal saldo;

   public String getFecha() {
      return this.fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getCliente() {
      return this.cliente;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

   public BigInteger getVenta() {
      return this.venta;
   }

   public void setVenta(BigInteger venta) {
      this.venta = venta;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public BigDecimal getImporteCobrado() {
      return this.importeCobrado;
   }

   public void setImporteCobrado(BigDecimal importeCobrado) {
      this.importeCobrado = importeCobrado;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }
}
