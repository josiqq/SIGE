package com.sige.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Membresia {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha = LocalDate.now();
   @NotNull(
      message = "Debe informar cliente!!"
   )
   @JoinColumn
   @ManyToOne
   private Cliente cliente;
   @NotNull(
      message = "Debe informar cuenta!!"
   )
   @JoinColumn
   @ManyToOne
   private Cuenta cuenta;
   private String observacion;
   @NotNull(
      message = "Importe es obligatorio!"
   )
   private BigDecimal importe;
   @JoinColumn(
      name = "id_instructor"
   )
   @ManyToOne
   private Instructor instructor;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaEstimado;
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime hora = LocalTime.now();

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

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public LocalDate getFechaEstimado() {
      return this.fechaEstimado;
   }

   public void setFechaEstimado(LocalDate fechaEstimado) {
      this.fechaEstimado = fechaEstimado;
   }

   public LocalTime getHora() {
      return this.hora;
   }

   public void setHora(LocalTime hora) {
      this.hora = hora;
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
         Membresia other = (Membresia)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
