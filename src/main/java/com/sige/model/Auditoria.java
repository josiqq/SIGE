package com.sige.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Auditoria {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   private Long registro;
   private String tabla;
   private String producto;
   private String codigo;
   private String tipo;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   @DateTimeFormat(
      pattern = "HH:mm:ss"
   )
   private LocalTime hora;
   private String usuario;
   private String precio;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getRegistro() {
      return this.registro;
   }

   public void setRegistro(Long registro) {
      this.registro = registro;
   }

   public String getTabla() {
      return this.tabla;
   }

   public void setTabla(String tabla) {
      this.tabla = tabla;
   }

   public String getProducto() {
      return this.producto;
   }

   public void setProducto(String producto) {
      this.producto = producto;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public LocalTime getHora() {
      return this.hora;
   }

   public void setHora(LocalTime hora) {
      this.hora = hora;
   }

   public String getPrecio() {
      return this.precio;
   }

   public void setPrecio(String precio) {
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
         Auditoria other = (Auditoria)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
