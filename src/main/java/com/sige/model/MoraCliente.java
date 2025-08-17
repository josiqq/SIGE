package com.sige.model;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
   name = "mora_cliente"
)
public class MoraCliente {
   @Id
   private Long id;
   private String cliente;
   private String documento;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaIngreso;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaEstimado;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private Integer dias;
   private String foto;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCliente() {
      return this.cliente;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

   public String getDocumento() {
      return this.documento;
   }

   public void setDocumento(String documento) {
      this.documento = documento;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public LocalDate getFechaIngreso() {
      return this.fechaIngreso;
   }

   public void setFechaIngreso(LocalDate fechaIngreso) {
      this.fechaIngreso = fechaIngreso;
   }

   public Integer getDias() {
      return this.dias;
   }

   public void setDias(Integer dias) {
      this.dias = dias;
   }

   public String getFoto() {
      return this.foto;
   }

   public void setFoto(String foto) {
      this.foto = foto;
   }

   public LocalDate getFechaEstimado() {
      return this.fechaEstimado;
   }

   public void setFechaEstimado(LocalDate fechaEstimado) {
      this.fechaEstimado = fechaEstimado;
   }

   public String fotoONada() {
      return !StringUtils.isEmpty(this.foto) ? this.foto : "sinfoto.png";
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
         MoraCliente other = (MoraCliente)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
