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
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class TransferenciaEfectivo {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta_origen"
   )
   @NotNull(
      message = "Debe informar la cuenta origen!"
   )
   private Cuenta cuentaOrigen;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta_destino"
   )
   @NotNull(
      message = "Debe informar la cuenta destino!"
   )
   private Cuenta cuentaDestino;
   private BigDecimal importe = BigDecimal.ZERO;
   private String observacion;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   @ManyToOne
   @JoinColumn(
      name = "id_condicion"
   )
   private Condicion condicion;
   @ManyToOne
   @JoinColumn(
      name = "id_planilla"
   )
   private Planilla planilla;
   @ManyToOne
   @JoinColumn(
      name = "id_planilla_destino"
   )
   private Planilla planillaDestino;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_destino"
   )
   private Moneda monedaDestino;

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

   public Cuenta getCuentaOrigen() {
      return this.cuentaOrigen;
   }

   public void setCuentaOrigen(Cuenta cuentaOrigen) {
      this.cuentaOrigen = cuentaOrigen;
   }

   public Cuenta getCuentaDestino() {
      return this.cuentaDestino;
   }

   public void setCuentaDestino(Cuenta cuentaDestino) {
      this.cuentaDestino = cuentaDestino;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public Condicion getCondicion() {
      return this.condicion;
   }

   public void setCondicion(Condicion condicion) {
      this.condicion = condicion;
   }

   public Planilla getPlanilla() {
      return this.planilla;
   }

   public void setPlanilla(Planilla planilla) {
      this.planilla = planilla;
   }

   public Planilla getPlanillaDestino() {
      return this.planillaDestino;
   }

   public void setPlanillaDestino(Planilla planillaDestino) {
      this.planillaDestino = planillaDestino;
   }

   public Moneda getMonedaDestino() {
      return this.monedaDestino;
   }

   public void setMonedaDestino(Moneda monedaDestino) {
      this.monedaDestino = monedaDestino;
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
         TransferenciaEfectivo other = (TransferenciaEfectivo)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
