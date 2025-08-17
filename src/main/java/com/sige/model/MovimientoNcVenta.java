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
public class MovimientoNcVenta {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   public Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_item_venta"
   )
   public ItemVenta itemVenta;
   @ManyToOne
   @JoinColumn(
      name = "id_venta"
   )
   public Venta venta;
   public BigDecimal cantidad;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   public Producto producto;
   public Long nc_venta;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public ItemVenta getItemVenta() {
      return this.itemVenta;
   }

   public void setItemVenta(ItemVenta itemVenta) {
      this.itemVenta = itemVenta;
   }

   public Venta getVenta() {
      return this.venta;
   }

   public void setVenta(Venta venta) {
      this.venta = venta;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public Long getNc_venta() {
      return this.nc_venta;
   }

   public void setNc_venta(Long nc_venta) {
      this.nc_venta = nc_venta;
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
         MovimientoNcVenta other = (MovimientoNcVenta)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
