package com.sige.model;

public enum CondicionCobro {
   EFECTIVO("Efectivo"),
   TARJETA("Tarjeta"),
   TRANSFERENCIA("Transferencia");

   String descripcion;

   private CondicionCobro(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
