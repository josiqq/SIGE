package com.sige.model;

import java.time.LocalDate;
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
public class AjustePrecio {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   @NotNull(
      message = "Debe informar el precio!"
   )
   private Precio precio;
   @OneToMany(
      mappedBy = "ajustePrecio",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   private List<ItemAjustePrecio> items = new ArrayList<>();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha = LocalDate.now();
   @Transient
   private String uuid;
   @ManyToOne
   @JoinColumn(
      name = "id_item_compra"
   )
   private ItemCompra itemCompra;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
   }

   public List<ItemAjustePrecio> getItems() {
      return this.items;
   }

   public void setItems(List<ItemAjustePrecio> items) {
      this.items = items;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void cargarItemAjustePrecio(List<ItemAjustePrecio> items) {
      this.items = items;
      this.items.forEach(i -> i.setAjustePrecio(this));
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public ItemCompra getItemCompra() {
      return this.itemCompra;
   }

   public void setItemCompra(ItemCompra itemCompra) {
      this.itemCompra = itemCompra;
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
         AjustePrecio other = (AjustePrecio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
