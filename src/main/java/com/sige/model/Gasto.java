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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Gasto {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el número de factura"
   )
   private String factura;
   @JoinColumn
   @ManyToOne
   @NotNull(
      message = "Debe informar el proveedor"
   )
   private Proveedor proveedor;
   @JoinColumn
   @ManyToOne
   @NotNull(
      message = "Debe informar la cuenta"
   )
   private Cuenta cuenta;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha!"
   )
   private LocalDate fecha = LocalDate.now();
   private String observacion;
   @NotNull(
      message = "Importe es obligatorio!"
   )
   private BigDecimal importe;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   @ManyToOne
   @JoinColumn(
      name = "id_planilla"
   )
   private Planilla planilla;
   @ManyToOne
   @JoinColumn(
      name = "id_condicion"
   )
   private Condicion condicion;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFactura() {
      return this.factura;
   }

   public void setFactura(String factura) {
      this.factura = factura;
   }

   public Proveedor getProveedor() {
      return this.proveedor;
   }

   public void setProveedor(Proveedor proveedor) {
      this.proveedor = proveedor;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public Planilla getPlanilla() {
      return this.planilla;
   }

   public void setPlanilla(Planilla planilla) {
      this.planilla = planilla;
   }

   public Condicion getCondicion() {
      return this.condicion;
   }

   public void setCondicion(Condicion condicion) {
      this.condicion = condicion;
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
         Gasto other = (Gasto)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
