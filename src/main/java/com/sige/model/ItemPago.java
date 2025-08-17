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
public class ItemPago {
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
   @ManyToOne
   @JoinColumn(
      name = "id_pago"
   )
   private Pago pago;
   private BigDecimal importe;

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

   public Pago getPago() {
      return this.pago;
   }

   public void setPago(Pago pago) {
      this.pago = pago;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
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
         ItemPago other = (ItemPago)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
