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
public class ItemApertura {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;
   @ManyToOne
   @JoinColumn(
      name = "id_condicion"
   )
   private Condicion condicion;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   @ManyToOne
   @JoinColumn(
      name = "id_apertura"
   )
   private Apertura apertura;
   private BigDecimal importe;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public Condicion getCondicion() {
      return this.condicion;
   }

   public void setCondicion(Condicion condicion) {
      this.condicion = condicion;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public Apertura getApertura() {
      return this.apertura;
   }

   public void setApertura(Apertura apertura) {
      this.apertura = apertura;
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
         ItemApertura other = (ItemApertura)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
