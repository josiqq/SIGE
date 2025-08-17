package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Moneda {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre!"
   )
   private String nombre;
   private int decimales = 0;
   private String sigla;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public int getDecimales() {
      return this.decimales;
   }

   public void setDecimales(int decimales) {
      this.decimales = decimales;
   }

   public String getSigla() {
      return this.sigla;
   }

   public void setSigla(String sigla) {
      this.sigla = sigla;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         Moneda other = (Moneda)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
