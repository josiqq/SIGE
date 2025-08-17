package com.sige.repository.filter;

public class ProductoFilter {
   private String nombreCodigo;
   private Integer limite;

   public String getNombreCodigo() {
      return this.nombreCodigo;
   }

   public void setNombreCodigo(String nombreCodigo) {
      this.nombreCodigo = nombreCodigo;
   }

   public Integer getLimite() {
      return this.limite;
   }

   public void setLimite(Integer limite) {
      this.limite = limite;
   }
}
