package com.sige.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ItemVentaLote {
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
   private String nroLote;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimiento;
   private BigDecimal cantidad;
   @ManyToOne
   @JoinColumn(
      name = "id_venta"
   )
   private Venta venta;
   @Transient
   private BigDecimal cantidadActual;

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

   public String getNroLote() {
      return this.nroLote;
   }

   public void setNroLote(String nroLote) {
      this.nroLote = nroLote;
   }

   public LocalDate getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(LocalDate vencimiento) {
      this.vencimiento = vencimiento;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public Venta getVenta() {
      return this.venta;
   }

   public void setVenta(Venta venta) {
      this.venta = venta;
   }

   public BigDecimal getCantidadActual() {
      return this.cantidadActual;
   }

   public void setCantidadActual(BigDecimal cantidadActual) {
      this.cantidadActual = cantidadActual;
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
         ItemVentaLote other = (ItemVentaLote)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
