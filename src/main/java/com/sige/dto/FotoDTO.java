package com.sige.dto;

public class FotoDTO {
   private String nombre;
   private String contentType;

   public FotoDTO(String nombre, String contentType) {
      this.nombre = nombre;
      this.contentType = contentType;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }
}
