package com.sige.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sige.model.Marca;

public class ProductoDTO {
   private Long id;
   private String nombre;
   private String codigo;
   private boolean pesable;
   private String foto;
   private BigDecimal costo;
   private Marca marca;

   public ProductoDTO(Long id, String nombre, String codigo, boolean pesable, String foto, BigDecimal costo, Marca marca) {
      this.id = id;
      this.nombre = nombre;
      this.codigo = codigo;
      this.pesable = pesable;
      this.foto = foto.isEmpty() ? "sinfoto.png" : foto;
      this.costo = costo.setScale(0, RoundingMode.UP);
      this.marca = marca;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
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
      this.foto = foto;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public Marca getMarca() {
      return this.marca;
   }

   public void setMarca(Marca marca) {
      this.marca = marca;
   }
}
