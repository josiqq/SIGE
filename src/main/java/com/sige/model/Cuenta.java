package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cuenta {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Nombre es obligatorio!!"
   )
   private String nombre;
   private BigDecimal saldo = BigDecimal.ZERO;
   private boolean activo;
   @ManyToOne
   @JoinColumn(
      name = "id_timbrado"
   )
   private Timbrado timbrado;
   private String identificador;
   private boolean cajaCobranza;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public Timbrado getTimbrado() {
      return this.timbrado;
   }

   public void setTimbrado(Timbrado timbrado) {
      this.timbrado = timbrado;
   }

   public String getIdentificador() {
      return this.identificador;
   }

   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

   public boolean isCajaCobranza() {
      return this.cajaCobranza;
   }

   public void setCajaCobranza(boolean cajaCobranza) {
      this.cajaCobranza = cajaCobranza;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
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
         Cuenta other = (Cuenta)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
