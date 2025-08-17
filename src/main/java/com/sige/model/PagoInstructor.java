package com.sige.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PagoInstructor {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotNull(
      message = "Debe informar Instructor!"
   )
   @JoinColumn(
      name = "id_instructor"
   )
   @ManyToOne
   private Instructor instructor;
   @NotNull(
      message = "Debe informar la cuenta!"
   )
   @JoinColumn(
      name = "id_cuenta"
   )
   @ManyToOne
   private Cuenta cuenta;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha;
   private String Observacion;
   @NotNull(
      message = "Importe es obligatorio!"
   )
   @Min(
      value = 1L,
      message = "El importe no puede ser cero!"
   )
   private BigDecimal importe;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public String getObservacion() {
      return this.Observacion;
   }

   public void setObservacion(String observacion) {
      this.Observacion = observacion;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
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
         PagoInstructor other = (PagoInstructor)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
