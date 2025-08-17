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
public class MovimientoVendedor {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private BigDecimal debito;
   private BigDecimal credito;
   private String observacion;
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   private Vendedor vendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_cobro_servicio"
   )
   private CobroServicio cobroServicio;
   @ManyToOne
   @JoinColumn(
      name = "id_pago_vendedor"
   )
   private PagoVendedor pagoVendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_item_venta"
   )
   private ItemVenta itemVenta;

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

   public BigDecimal getDebito() {
      return this.debito;
   }

   public void setDebito(BigDecimal debito) {
      this.debito = debito;
   }

   public BigDecimal getCredito() {
      return this.credito;
   }

   public void setCredito(BigDecimal credito) {
      this.credito = credito;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public CobroServicio getCobroServicio() {
      return this.cobroServicio;
   }

   public void setCobroServicio(CobroServicio cobroServicio) {
      this.cobroServicio = cobroServicio;
   }

   public PagoVendedor getPagoVendedor() {
      return this.pagoVendedor;
   }

   public void setPagoVendedor(PagoVendedor pagoVendedor) {
      this.pagoVendedor = pagoVendedor;
   }

   public ItemVenta getItemVenta() {
      return this.itemVenta;
   }

   public void setItemVenta(ItemVenta itemVenta) {
      this.itemVenta = itemVenta;
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
         MovimientoVendedor other = (MovimientoVendedor)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
