package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ItemAgenda {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime hora;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(
      name = "id_agenda"
   )
   @JsonIgnore
   private Agenda agenda;
   private String observacion;
   private Estado estado;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalTime getHora() {
      return this.hora;
   }

   public void setHora(LocalTime hora) {
      this.hora = hora;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Agenda getAgenda() {
      return this.agenda;
   }

   public void setAgenda(Agenda agenda) {
      this.agenda = agenda;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public Estado getEstado() {
      return this.estado;
   }

   public void setEstado(Estado estado) {
      this.estado = estado;
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
         ItemAgenda other = (ItemAgenda)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
