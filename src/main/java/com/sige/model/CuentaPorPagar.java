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
public class CuentaPorPagar {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_compra"
   )
   private Compra compra;
   @DateTimeFormat(
      pattern = "dd/MM/yyy"
   )
   private LocalDate fecha;
   private BigDecimal importe;
   private BigDecimal importePagado;
   @DateTimeFormat(
      pattern = "dd/MM/yyy"
   )
   private LocalDate vencimiento;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Compra getCompra() {
      return this.compra;
   }

   public void setCompra(Compra compra) {
      this.compra = compra;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public BigDecimal getImportePagado() {
      return this.importePagado;
   }

   public void setImportePagado(BigDecimal importePagado) {
      this.importePagado = importePagado;
   }

   public LocalDate getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(LocalDate vencimiento) {
      this.vencimiento = vencimiento;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
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
         CuentaPorPagar other = (CuentaPorPagar)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
