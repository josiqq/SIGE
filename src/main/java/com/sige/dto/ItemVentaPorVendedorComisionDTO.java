package com.sige.dto;

import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;

public class ItemVentaPorVendedorComisionDTO {
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private String fecha;
   private String cliente;
   private String producto;
   private String venta;
   private BigDecimal cantidad;
   private BigDecimal total;
   private BigDecimal porcComision;
   private BigDecimal comision;

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

   public String getProducto() {
      return this.producto;
   }

   public void setProducto(String producto) {
      this.producto = producto;
   }

   public String getVenta() {
      return this.venta;
   }

   public void setVenta(String venta) {
      this.venta = venta;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getPorcComision() {
      return this.porcComision;
   }

   public void setPorcComision(BigDecimal porcComision) {
      this.porcComision = porcComision;
   }

   public BigDecimal getComision() {
      return this.comision;
   }

   public void setComision(BigDecimal comision) {
      this.comision = comision;
   }
}
