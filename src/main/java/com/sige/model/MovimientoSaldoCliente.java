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
public class MovimientoSaldoCliente {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_anticipo_cliente"
   )
   private AnticipoCliente anticipoCliente;
   @ManyToOne
   @JoinColumn(
      name = "id_cobro_servicio"
   )
   private CobroServicio cobroServicio;
   private BigDecimal debito;
   private BigDecimal credito;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   private Cliente cliente;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public AnticipoCliente getAnticipoCliente() {
      return this.anticipoCliente;
   }

   public void setAnticipoCliente(AnticipoCliente anticipoCliente) {
      this.anticipoCliente = anticipoCliente;
   }

   public CobroServicio getCobroServicio() {
      return this.cobroServicio;
   }

   public void setCobroServicio(CobroServicio cobroServicio) {
      this.cobroServicio = cobroServicio;
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

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
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
         MovimientoSaldoCliente other = (MovimientoSaldoCliente)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
