package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class TransferenciaStock {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito_origen"
   )
   @NotNull(
      message = "Debe informar el deposito origen"
   )
   private Deposito depositoOrigen;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito_destino"
   )
   @NotNull(
      message = "Debe informar el deposito destino"
   )
   private Deposito depositoDestino;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @DateTimeFormat(
      pattern = "HH:mm:ss"
   )
   private LocalTime hora;
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   private String observacion;
   @OneToMany(
      mappedBy = "transferenciaStock",
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemTransferenciaStock> items = new ArrayList<>();
   @Transient
   private String uuid;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Deposito getDepositoOrigen() {
      return this.depositoOrigen;
   }

   public void setDepositoOrigen(Deposito depositoOrigen) {
      this.depositoOrigen = depositoOrigen;
   }

   public Deposito getDepositoDestino() {
      return this.depositoDestino;
   }

   public void setDepositoDestino(Deposito depositoDestino) {
      this.depositoDestino = depositoDestino;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public LocalTime getHora() {
      return this.hora;
   }

   public void setHora(LocalTime hora) {
      this.hora = hora;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public List<ItemTransferenciaStock> getItems() {
      return this.items;
   }

   public void setItems(List<ItemTransferenciaStock> items) {
      this.items = items;
   }

   public void adicionarTransferencia(List<ItemTransferenciaStock> items) {
      this.items = items;
      this.items.forEach(i -> i.setTransferenciaStock(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
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
         TransferenciaStock other = (TransferenciaStock)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
