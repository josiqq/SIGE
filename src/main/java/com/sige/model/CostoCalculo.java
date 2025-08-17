package com.sige.model;

public enum CostoCalculo {
   PRECIOCOMPRA("Precio de compra"),
   PROMEDIO("Promedio");

   private String descripcion;

   private CostoCalculo(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
