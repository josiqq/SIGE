package com.sige.model;

import java.time.LocalDate;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Agenda {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   private Vendedor vendedor;
   @Transient
   private String uuid;
   @OneToMany(
      mappedBy = "agenda",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   private List<ItemAgenda> items;

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

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public List<ItemAgenda> getItems() {
      return this.items;
   }

   public void setItems(List<ItemAgenda> items) {
      this.items = items;
   }

   public void cargarItems(List<ItemAgenda> items) {
      this.items = items;
      this.items.forEach(i -> i.setAgenda(this));
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
         Agenda other = (Agenda)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
