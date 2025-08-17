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
public class Cotizacion {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha"
   )
   private LocalDate fecha = LocalDate.now();
   @NotNull(
      message = "Debe informar moneda origen!"
   )
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_origen"
   )
   private Moneda monedaOrigen;
   @NotNull(
      message = "Debe informar moneda destino!"
   )
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_destino"
   )
   private Moneda monedaDestino;
   private boolean multiplicar;
   private boolean dividir;
   private BigDecimal valor = BigDecimal.ZERO;
   private boolean modificado;

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

   public Moneda getMonedaOrigen() {
      return this.monedaOrigen;
   }

   public void setMonedaOrigen(Moneda monedaOrigen) {
      this.monedaOrigen = monedaOrigen;
   }

   public Moneda getMonedaDestino() {
      return this.monedaDestino;
   }

   public void setMonedaDestino(Moneda monedaDestino) {
      this.monedaDestino = monedaDestino;
   }

   public boolean isMultiplicar() {
      return this.multiplicar;
   }

   public void setMultiplicar(boolean multiplicar) {
      this.multiplicar = multiplicar;
   }

   public boolean isDividir() {
      return this.dividir;
   }

   public void setDividir(boolean dividir) {
      this.dividir = dividir;
   }

   public BigDecimal getValor() {
      return this.valor;
   }

   public void setValor(BigDecimal valor) {
      this.valor = valor;
   }

   public boolean isModificado() {
      return this.modificado;
   }

   public void setModificado(boolean modificado) {
      this.modificado = modificado;
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
         Cotizacion other = (Cotizacion)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
