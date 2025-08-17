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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Nombre es obligatorio!!"
   )
   private String nombre;
   private String documento;
   private String telefono;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaIngreso = LocalDate.now();
   private boolean activo = true;
   private String foto;
   private String contentType;
   private BigDecimal tarifa = BigDecimal.ZERO;
   private BigDecimal Saldo = BigDecimal.ZERO;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaUltimoPago;
   @Transient
   public boolean nuevaFoto;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   private Precio precio;

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

   public String getDocumento() {
      return this.documento;
   }

   public void setDocumento(String documento) {
      this.documento = documento;
   }

   public String getTelefono() {
      return this.telefono;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public LocalDate getFechaIngreso() {
      return this.fechaIngreso;
   }

   public void setFechaIngreso(LocalDate fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   public boolean isNuevaFoto() {
      return this.nuevaFoto;
   }

   public void setNuevaFoto(boolean nuevaFoto) {
      this.nuevaFoto = nuevaFoto;
   }

   public String fotoONada() {
      return !StringUtils.isEmpty(this.foto) ? this.foto : "sinfoto.png";
   }

   public BigDecimal getSaldo() {
      return this.Saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.Saldo = saldo;
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
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
         Cliente other = (Cliente)obj;
         return Objects.equals(this.id, other.id);
      }
   }

   public BigDecimal getTarifa() {
      return this.tarifa;
   }

   public void setTarifa(BigDecimal tarifa) {
      this.tarifa = tarifa;
   }

   public LocalDate getFechaUltimoPago() {
      return this.fechaUltimoPago;
   }

   public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
      this.fechaUltimoPago = fechaUltimoPago;
   }
}
