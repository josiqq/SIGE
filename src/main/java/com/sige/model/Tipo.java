package com.sige.model;

public enum Tipo {
   INSERT("insert"),
   DELETE("delete"),
   UPDATE("update");

   private String descripcion;

   private Tipo(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
