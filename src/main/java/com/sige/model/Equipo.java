package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import org.apache.groovy.parser.antlr4.util.StringUtils;

@Entity
public class Equipo {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar Nombre del Equipo!"
   )
   private String nombre;
   @NotBlank(
      message = "Nombre corto es obligatorio, este aparecerá en las marcaciones!"
   )
   private String nombreCorto;
   private String foto;
   private String contentType;
   @Transient
   public boolean nuevaFoto;

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

   public String getNombreCorto() {
      return this.nombreCorto;
   }

   public void setNombreCorto(String nombreCorto) {
      this.nombreCorto = nombreCorto;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public boolean isNuevaFoto() {
      return this.nuevaFoto;
   }

   public void setNuevaFoto(boolean nuevaFoto) {
      this.nuevaFoto = nuevaFoto;
   }

   public String fotoONada() {
      return !StringUtils.isEmpty(this.foto) ? this.foto : "sinfoto.png";
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
         Equipo other = (Equipo)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
