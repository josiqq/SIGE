package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductoStockDTO {
   private BigInteger id;
   private String codigo;
   private String nombre;
   private BigDecimal costo;
   private boolean pesable;
   private String foto;
   private String marca;
   private BigDecimal precioMinimo;
   private BigDecimal precio;
   private BigDecimal cantidad;
   private String deposito;
   private boolean conLote;
   private BigDecimal stockMinimo;

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

   public boolean isPesable() {
      return this.pesable;
   }

   public void setPesable(boolean pesable) {
      this.pesable = pesable;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public String getMarca() {
      return this.marca;
   }

   public void setMarca(String marca) {
      this.marca = marca;
   }

   public BigDecimal getPrecioMinimo() {
      return this.precioMinimo;
   }

   public void setPrecioMinimo(BigDecimal precioMinimo) {
      this.precioMinimo = precioMinimo;
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

   public String getDeposito() {
      return this.deposito;
   }

   public void setDeposito(String deposito) {
      this.deposito = deposito;
   }

   public boolean isConLote() {
      return this.conLote;
   }

   public void setConLote(boolean conLote) {
      this.conLote = conLote;
   }

   public BigDecimal getStockMinimo() {
      return this.stockMinimo;
   }

   public void setStockMinimo(BigDecimal stockMinimo) {
      this.stockMinimo = stockMinimo;
   }
}
