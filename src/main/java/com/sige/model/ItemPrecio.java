package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemPrecio {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;
   private BigDecimal costo;
   private BigDecimal precioMinimo = BigDecimal.ZERO;
   private BigDecimal precioProducto;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   private Precio precio;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public BigDecimal getPrecioMinimo() {
      return this.precioMinimo;
   }

   public void setPrecioMinimo(BigDecimal precioMinimo) {
      this.precioMinimo = precioMinimo;
   }

   public BigDecimal getPrecioProducto() {
      return this.precioProducto;
   }

   public void setPrecioProducto(BigDecimal precioProducto) {
      this.precioProducto = precioProducto;
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
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
         ItemPrecio other = (ItemPrecio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
