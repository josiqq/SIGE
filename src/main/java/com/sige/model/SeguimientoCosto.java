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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SeguimientoCosto {
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
   private Long registro;
   private String observacion;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private BigDecimal cantidadActual;
   private BigDecimal costoActual;
   private BigDecimal cantidadCompra;
   private BigDecimal precioCompra;
   private BigDecimal sumatoriaActual;
   private BigDecimal sumatoriaCompra;
   private BigDecimal costoTotal;
   private BigDecimal cantidadTotal;
   private BigDecimal costoCalculo;
   private Long detalle;

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

   public Long getRegistro() {
      return this.registro;
   }

   public void setRegistro(Long registro) {
      this.registro = registro;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public BigDecimal getCantidadActual() {
      return this.cantidadActual;
   }

   public void setCantidadActual(BigDecimal cantidadActual) {
      this.cantidadActual = cantidadActual;
   }

   public BigDecimal getCostoActual() {
      return this.costoActual;
   }

   public void setCostoActual(BigDecimal costoActual) {
      this.costoActual = costoActual;
   }

   public BigDecimal getCantidadCompra() {
      return this.cantidadCompra;
   }

   public void setCantidadCompra(BigDecimal cantidadCompra) {
      this.cantidadCompra = cantidadCompra;
   }

   public BigDecimal getPrecioCompra() {
      return this.precioCompra;
   }

   public void setPrecioCompra(BigDecimal precioCompra) {
      this.precioCompra = precioCompra;
   }

   public BigDecimal getSumatoriaActual() {
      return this.sumatoriaActual;
   }

   public void setSumatoriaActual(BigDecimal sumatoriaActual) {
      this.sumatoriaActual = sumatoriaActual;
   }

   public BigDecimal getSumatoriaCompra() {
      return this.sumatoriaCompra;
   }

   public void setSumatoriaCompra(BigDecimal sumatoriaCompra) {
      this.sumatoriaCompra = sumatoriaCompra;
   }

   public BigDecimal getCostoTotal() {
      return this.costoTotal;
   }

   public void setCostoTotal(BigDecimal costoTotal) {
      this.costoTotal = costoTotal;
   }

   public BigDecimal getCantidadTotal() {
      return this.cantidadTotal;
   }

   public void setCantidadTotal(BigDecimal cantidadTotal) {
      this.cantidadTotal = cantidadTotal;
   }

   public BigDecimal getCostoCalculo() {
      return this.costoCalculo;
   }

   public void setCostoCalculo(BigDecimal costoCalculo) {
      this.costoCalculo = costoCalculo;
   }

   public Long getDetalle() {
      return this.detalle;
   }

   public void setDetalle(Long detalle) {
      this.detalle = detalle;
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
         SeguimientoCosto other = (SeguimientoCosto)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
