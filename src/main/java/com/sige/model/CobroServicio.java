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
public class CobroServicio {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   @NotNull(
      message = "Debe informar el cliente!"
   )
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   private Vendedor vendedor;
   @NotNull(
      message = "Importe es obrigatorio!"
   )
   private BigDecimal importe = BigDecimal.ZERO;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   @NotNull(
      message = "Debe informar la cuenta!"
   )
   private Cuenta cuenta;
   private String Observacion;
   private BigDecimal valor = BigDecimal.ZERO;
   private BigDecimal saldo = BigDecimal.ZERO;

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

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
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

   public String getObservacion() {
      return this.Observacion;
   }

   public void setObservacion(String observacion) {
      this.Observacion = observacion;
   }

   public BigDecimal getValor() {
      return this.valor;
   }

   public void setValor(BigDecimal valor) {
      this.valor = valor;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
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
         CobroServicio other = (CobroServicio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
