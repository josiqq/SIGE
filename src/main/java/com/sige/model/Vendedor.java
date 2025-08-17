package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Vendedor {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre!"
   )
   private String nombre;
   @NotBlank(
      message = "Debe informar el documento!"
   )
   private String documento;
   private String telefono;
   private BigDecimal porcentaje = BigDecimal.ZERO;
   private BigDecimal saldo = BigDecimal.ZERO;
   private boolean activo;
   private boolean supervisor;

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

   public String getDocumento() {
      return this.documento;
   }

   public void setDocumento(String documento) {
      this.documento = documento;
   }

   public String getTelefono() {
      return this.telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public BigDecimal getPorcentaje() {
      return this.porcentaje;
   }

   public void setPorcentaje(BigDecimal porcentaje) {
      this.porcentaje = porcentaje;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public boolean isSupervisor() {
      return this.supervisor;
   }

   public void setSupervisor(boolean supervisor) {
      this.supervisor = supervisor;
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
         Vendedor other = (Vendedor)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
