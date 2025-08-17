package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AjusteStock {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   @NotNull(
      message = "Debe informar el deposito!"
   )
   private Deposito deposito;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha = LocalDate.now();
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime hora = LocalTime.now();
   private boolean confirmado;
   private String observacion;
   @OneToMany(
      mappedBy = "ajusteStock",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemAjusteStock> items = new ArrayList<>();
   @Transient
   private String uuid;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public boolean isConfirmado() {
      return this.confirmado;
   }

   public void setConfirmado(boolean confirmado) {
      this.confirmado = confirmado;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public List<ItemAjusteStock> getItems() {
      return this.items;
   }

   public void setItems(List<ItemAjusteStock> items) {
      this.items = items;
   }

   public void agregarAjusteAItem(List<ItemAjusteStock> items) {
      this.items = items;
      this.items.forEach(i -> i.setAjusteStock(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
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
         AjusteStock other = (AjusteStock)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
