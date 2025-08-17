package com.sige.repository.filter;

public class MoraClienteFilter {
   private String nombreDocumento;
   private Integer limite;

   public String getNombreDocumento() {
      return this.nombreDocumento;
   }

   public void setNombreDocumento(String nombreDocumento) {
      this.nombreDocumento = nombreDocumento;
   }

   public Integer getLimite() {
      return this.limite;
   }

   public void setLimite(Integer limite) {
      this.limite = limite;
   }
}
