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
public class Lote {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   public Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   public Producto producto;
   public String nroLote;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate vencimiento;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   public Deposito deposito;
   public BigDecimal cantidad;

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

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
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
         Lote other = (Lote)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
