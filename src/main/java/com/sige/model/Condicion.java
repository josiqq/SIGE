package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Condicion {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre !"
   )
   private String nombre;
   private CondicionCobro condicionCobro;
   private BigDecimal comision = BigDecimal.ZERO;

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

   public CondicionCobro getCondicionCobro() {
      return this.condicionCobro;
   }

   public void setCondicionCobro(CondicionCobro condicionCobro) {
      this.condicionCobro = condicionCobro;
   }

   public BigDecimal getComision() {
      return this.comision;
   }

   public void setComision(BigDecimal comision) {
      this.comision = comision;
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
         Condicion other = (Condicion)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
