package com.sige.dto;

import java.math.BigInteger;

public class ClienteConsorcioDTO {
   private BigInteger id;
   private String nombre;

   public BigInteger getId() {
      return this.id;
   }

   public void setId(BigInteger id) {
      this.id = id;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }
}
