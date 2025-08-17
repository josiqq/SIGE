package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemPlanillaRendicion {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
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
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal importe;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta_destino"
   )
   private Cuenta cuentaDestino;
   private BigDecimal importeMn;
   @ManyToOne
   @JoinColumn(
      name = "id_planilla"
   )
   private Planilla planilla;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public Planilla getPlanilla() {
      return this.planilla;
   }

   public void setPlanilla(Planilla planilla) {
      this.planilla = planilla;
   }

   public Cuenta getCuentaDestino() {
      return this.cuentaDestino;
   }

   public void setCuentaDestino(Cuenta cuentaDestino) {
      this.cuentaDestino = cuentaDestino;
   }

   public BigDecimal getImporteMn() {
      return this.importeMn;
   }

   public void setImporteMn(BigDecimal importeMn) {
      this.importeMn = importeMn;
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
         ItemPlanillaRendicion other = (ItemPlanillaRendicion)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
