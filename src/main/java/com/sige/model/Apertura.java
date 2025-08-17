package com.sige.model;

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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Apertura {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @OneToMany(
      mappedBy = "apertura",
      orphanRemoval = true
   )
   private List<ItemApertura> items = new ArrayList<>();
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   @Transient
   private String uuid;

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

   public List<ItemApertura> getItems() {
      return this.items;
   }

   public void setItems(List<ItemApertura> items) {
      this.items = items;
   }

   public void adicionarItem(List<ItemApertura> items) {
      this.items = items;
      this.items.forEach(i -> i.setApertura(this));
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
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
         Apertura other = (Apertura)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
