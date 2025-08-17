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
public class ItemPresupuestoCompra {
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
   private BigDecimal cantidad;
   private BigDecimal precio;
   @ManyToOne
   @JoinColumn(
      name = "id_presupuesto_compra"
   )
   private PresupuestoCompra presupuestoCompra;

   public BigDecimal getSubTotal() {
      return this.cantidad.multiply(this.precio);
   }

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

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public PresupuestoCompra getPresupuestoCompra() {
      return this.presupuestoCompra;
   }

   public void setPresupuestoCompra(PresupuestoCompra presupuestoCompra) {
      this.presupuestoCompra = presupuestoCompra;
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
         ItemPresupuestoCompra other = (ItemPresupuestoCompra)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
