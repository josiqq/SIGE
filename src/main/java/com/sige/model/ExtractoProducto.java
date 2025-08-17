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
public class ExtractoProducto {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   private Deposito deposito;
   private String observacion;
   private BigDecimal entrada;
   private BigDecimal salida;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;
   private Long registro;
   private Long id_detalle;

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

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public BigDecimal getEntrada() {
      return this.entrada;
   }

   public void setEntrada(BigDecimal entrada) {
      this.entrada = entrada;
   }

   public BigDecimal getSalida() {
      return this.salida;
   }

   public void setSalida(BigDecimal salida) {
      this.salida = salida;
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

   public Long getId_detalle() {
      return this.id_detalle;
   }

   public void setId_detalle(Long id_detalle) {
      this.id_detalle = id_detalle;
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
         ExtractoProducto other = (ExtractoProducto)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
