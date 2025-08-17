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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class MovInstructor {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private String observacion;
   @JoinColumn(
      name = "id_membresia"
   )
   @ManyToOne
   private Membresia membresia;
   @JoinColumn(
      name = "id_instructor"
   )
   @ManyToOne
   private Instructor instructor;
   @JoinColumn(
      name = "id_pago_instructor"
   )
   @ManyToOne
   private PagoInstructor pagoInstructor;
   private BigDecimal credito;
   private BigDecimal debito;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public Membresia getMembresia() {
      return this.membresia;
   }

   public void setMembresia(Membresia membresia) {
      this.membresia = membresia;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public BigDecimal getCredito() {
      return this.credito;
   }

   public void setCredito(BigDecimal credito) {
      this.credito = credito;
   }

   public BigDecimal getDebito() {
      return this.debito;
   }

   public void setDebito(BigDecimal debito) {
      this.debito = debito;
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
         MovInstructor other = (MovInstructor)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
