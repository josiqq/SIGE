package com.sige.dto;

import java.math.BigDecimal;

public class CotizacionesDTO {
   private String nombre;
   private String sigla;
   private BigDecimal valor;
   private boolean multiplicar;
   private boolean dividir;
   private int decimales;

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getSigla() {
      return this.sigla;
   }

   public void setSigla(String sigla) {
      this.sigla = sigla;
   }

   public BigDecimal getValor() {
      return this.valor;
   }

   public void setValor(BigDecimal valor) {
      this.valor = valor;
   }

   public boolean isMultiplicar() {
      return this.multiplicar;
   }

   public void setMultiplicar(boolean multiplicar) {
      this.multiplicar = multiplicar;
   }

   public boolean isDividir() {
      return this.dividir;
   }

   public void setDividir(boolean dividir) {
      this.dividir = dividir;
   }

   public int getDecimales() {
      return this.decimales;
   }

   public void setDecimales(int decimales) {
      this.decimales = decimales;
   }
}
