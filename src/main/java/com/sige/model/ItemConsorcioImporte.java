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
public class ItemConsorcioImporte {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private BigDecimal monto;
   private BigDecimal montoCobrado;
   private BigDecimal saldo;
   @ManyToOne
   @JoinColumn(
      name = "id_item_consorcio"
   )
   private ItemConsorcio itemConsorcio;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;

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

   public BigDecimal getMonto() {
      return this.monto;
   }

   public void setMonto(BigDecimal monto) {
      this.monto = monto;
   }

   public BigDecimal getMontoCobrado() {
      return this.montoCobrado;
   }

   public void setMontoCobrado(BigDecimal montoCobrado) {
      this.montoCobrado = montoCobrado;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public ItemConsorcio getItemConsorcio() {
      return this.itemConsorcio;
   }

   public void setItemConsorcio(ItemConsorcio itemConsorcio) {
      this.itemConsorcio = itemConsorcio;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
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
         ItemConsorcioImporte other = (ItemConsorcioImporte)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
