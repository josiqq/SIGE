package com.sige.model;

public enum Estado {
   RESERVADO("Reservado"),
   LIBRE("Libre"),
   CANCELADO("Cancelado"),
   TERMINADO("Terminado");

   private String descripcion;

   private Estado(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
