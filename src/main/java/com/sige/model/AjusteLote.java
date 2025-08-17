package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
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
public class AjusteLote {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   public Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   public LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   @NotNull(
      message = "Debe informar el deposito"
   )
   public Deposito deposito;
   @OneToMany(
      mappedBy = "ajusteLote",
      orphanRemoval = true
   )
   @JsonIgnore
   public List<ItemAjusteLote> items = new ArrayList<>();
   @Transient
   private String uuid;
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   private String observacion;

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

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public List<ItemAjusteLote> getItems() {
      return this.items;
   }

   public void setItems(List<ItemAjusteLote> items) {
      this.items = items;
   }

   public void agregarItem(List<ItemAjusteLote> items) {
      this.items = items;
      this.items.forEach(i -> i.setAjusteLote(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
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
         AjusteLote other = (AjusteLote)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
