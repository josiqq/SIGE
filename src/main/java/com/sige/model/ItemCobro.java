package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ItemCobro {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_venta"
   )
   private Venta venta;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private BigDecimal importe;
   @ManyToOne
   @JoinColumn(
      name = "id_cobro"
   )
   @JsonIgnore
   private Cobro cobro;
   @ManyToOne
   @JoinColumn(
      name = "id_comision_tarjeta"
   )
   private ComisionTarjeta comisionTarjeta;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Venta getVenta() {
      return this.venta;
   }

   public void setVenta(Venta venta) {
      this.venta = venta;
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

   public Cobro getCobro() {
      return this.cobro;
   }

   public void setCobro(Cobro cobro) {
      this.cobro = cobro;
   }

   public ComisionTarjeta getComisionTarjeta() {
      return this.comisionTarjeta;
   }

   public void setComisionTarjeta(ComisionTarjeta comisionTarjeta) {
      this.comisionTarjeta = comisionTarjeta;
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
         ItemCobro other = (ItemCobro)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
