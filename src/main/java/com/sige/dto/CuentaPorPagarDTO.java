package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CuentaPorPagarDTO {
   private String fecha;
   private String proveedor;
   private BigInteger compra;
   private BigDecimal importe;
   private BigDecimal importePagado;
   private BigDecimal saldo;
   private String vencimiento;

   public String getFecha() {
      return this.fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getProveedor() {
      return this.proveedor;
   }

   public void setProveedor(String proveedor) {
      this.proveedor = proveedor;
   }

   public BigInteger getCompra() {
      return this.compra;
   }

   public void setCompra(BigInteger compra) {
      this.compra = compra;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public BigDecimal getImportePagado() {
      return this.importePagado;
   }

   public void setImportePagado(BigDecimal importePagado) {
      this.importePagado = importePagado;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public String getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(String vencimiento) {
      this.vencimiento = vencimiento;
   }
}
