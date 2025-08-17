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
public class ItemAjustePrecio {
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
   private BigDecimal precioProducto;
   private BigDecimal utilidad;
   private BigDecimal precioMinimo;
   @ManyToOne
   @JoinColumn(
      name = "id_ajustePrecio"
   )
   private AjustePrecio ajustePrecio;

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

   public BigDecimal getPrecioProducto() {
      return this.precioProducto;
   }

   public void setPrecioProducto(BigDecimal precioProducto) {
      this.precioProducto = precioProducto;
   }

   public BigDecimal getUtilidad() {
      return this.utilidad;
   }

   public void setUtilidad(BigDecimal utilidad) {
      this.utilidad = utilidad;
   }

   public BigDecimal getPrecioMinimo() {
      return this.precioMinimo;
   }

   public void setPrecioMinimo(BigDecimal precioMinimo) {
      this.precioMinimo = precioMinimo;
   }

   public AjustePrecio getAjustePrecio() {
      return this.ajustePrecio;
   }

   public void setAjustePrecio(AjustePrecio ajustePrecio) {
      this.ajustePrecio = ajustePrecio;
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
         ItemAjustePrecio other = (ItemAjustePrecio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
