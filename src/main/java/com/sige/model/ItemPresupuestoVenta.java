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
public class ItemPresupuestoVenta {
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
   private BigDecimal costo;
   private BigDecimal precio;
   @ManyToOne
   @JoinColumn(
      name = "id_presupuesto_venta"
   )
   private PresupuestoVenta presupuestoVenta;

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

   public BigDecimal getCosto() {
      return this.costo;
   }

   public void setCosto(BigDecimal costo) {
      this.costo = costo;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public PresupuestoVenta getPresupuestoVenta() {
      return this.presupuestoVenta;
   }

   public void setPresupuestoVenta(PresupuestoVenta presupuestoVenta) {
      this.presupuestoVenta = presupuestoVenta;
   }

   public BigDecimal subTotal() {
      return this.cantidad.multiply(this.precio);
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
         ItemPresupuestoVenta other = (ItemPresupuestoVenta)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
