package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ProductoCotizadoDTO {
   private BigInteger id;
   private String nombre;
   private String codigo;
   private boolean pesable;
   private String foto;
   private BigDecimal costo;
   private String marca;

   public BigInteger getId() {
      return this.id;
   }

   public void setId(BigInteger id) {
      this.id = id;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
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
      this.foto = foto.isEmpty() ? "sinfoto.png" : foto;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public String getMarca() {
      return this.marca;
   }

   public void setMarca(String marca) {
      this.marca = marca;
   }
}
