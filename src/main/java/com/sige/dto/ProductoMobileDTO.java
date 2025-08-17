package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductoMobileDTO {
   private BigInteger id;
   private boolean pesable;
   private String codigo;
   private String nombre;
   private String marca;
   private BigDecimal cantidad;
   private BigDecimal precio;
   private BigDecimal costo;
   private String deposito;
   private String foto;

   public BigInteger getId() {
      return this.id;
   }

   public void setId(BigInteger id) {
      this.id = id;
   }

   public boolean isPesable() {
      return this.pesable;
   }

   public void setPesable(boolean pesable) {
      this.pesable = pesable;
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

   public String getMarca() {
      return this.marca;
   }

   public void setMarca(String marca) {
      this.marca = marca;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public String getDeposito() {
      return this.deposito;
   }

   public void setDeposito(String deposito) {
      this.deposito = deposito;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }
}
