package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StockValorizadoDTO {
   private BigInteger id;
   private String codigo;
   private String nombre;
   private BigDecimal costo;
   private BigDecimal precio;
   private BigDecimal cantidad;
   private BigDecimal totalCosto;
   private BigDecimal totalPrecio;
   private BigDecimal utilidad;
   private boolean pesable;

   public BigInteger getId() {
      return this.id;
   }

   public void setId(BigInteger id) {
      this.id = id;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public BigDecimal getTotalCosto() {
      return this.totalCosto;
   }

   public void setTotalCosto(BigDecimal totalCosto) {
      this.totalCosto = totalCosto;
   }

   public BigDecimal getTotalPrecio() {
      return this.totalPrecio;
   }

   public void setTotalPrecio(BigDecimal totalPrecio) {
      this.totalPrecio = totalPrecio;
   }

   public BigDecimal getUtilidad() {
      return this.utilidad;
   }

   public void setUtilidad(BigDecimal utilidad) {
      this.utilidad = utilidad;
   }

   public boolean isPesable() {
      return this.pesable;
   }

   public void setPesable(boolean pesable) {
      this.pesable = pesable;
   }
}
