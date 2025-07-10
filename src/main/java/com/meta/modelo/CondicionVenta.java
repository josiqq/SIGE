package com.meta.modelo;

public enum CondicionVenta {
   CONTADO("Contado"),
   CREDITO("Crédito");

   String descripcion;

   private CondicionVenta(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
