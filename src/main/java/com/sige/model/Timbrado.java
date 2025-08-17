package com.sige.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Timbrado {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotNull(
      message = "Debe informar el numero de timbrado!"
   )
   private Integer numero;
   @NotNull(
      message = "Debe informar la expedicion por ejemplo 001-001"
   )
   private String expedicion;
   @NotNull(
      message = "Debe informar la fecha de inicio!"
   )
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaInicio;
   @NotNull(
      message = "Debe informar la fecha final!"
   )
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaFin;
   @NotNull(
      message = "Debe informar la numeración inicial !"
   )
   private Integer numeracionIni;
   @NotNull(
      message = "Debe informar la numeración final !"
   )
   private Integer numeracionFin;
   private boolean activo = true;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Integer getNumero() {
      return this.numero;
   }

   public void setNumero(Integer numero) {
      this.numero = numero;
   }

   public LocalDate getFechaInicio() {
      return this.fechaInicio;
   }

   public void setFechaInicio(LocalDate fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   public LocalDate getFechaFin() {
      return this.fechaFin;
   }

   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }

   public Integer getNumeracionIni() {
      return this.numeracionIni;
   }

   public void setNumeracionIni(Integer numeracionIni) {
      this.numeracionIni = numeracionIni;
   }

   public Integer getNumeracionFin() {
      return this.numeracionFin;
   }

   public void setNumeracionFin(Integer numeracionFin) {
      this.numeracionFin = numeracionFin;
   }

   public String getExpedicion() {
      return this.expedicion;
   }

   public void setExpedicion(String expedicion) {
      this.expedicion = expedicion;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
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
         Timbrado other = (Timbrado)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
