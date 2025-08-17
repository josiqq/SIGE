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
public class ItemAjusteStock {
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
   private BigDecimal sumar;
   private BigDecimal restar;
   private BigDecimal cantidad;
   @ManyToOne
   @JoinColumn(
      name = "id_ajuste_stock"
   )
   private AjusteStock ajusteStock;

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

   public BigDecimal getSumar() {
      return this.sumar;
   }

   public void setSumar(BigDecimal sumar) {
      this.sumar = sumar;
   }

   public BigDecimal getRestar() {
      return this.restar;
   }

   public void setRestar(BigDecimal restar) {
      this.restar = restar;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public AjusteStock getAjusteStock() {
      return this.ajusteStock;
   }

   public void setAjusteStock(AjusteStock ajusteStock) {
      this.ajusteStock = ajusteStock;
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
         ItemAjusteStock other = (ItemAjusteStock)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
