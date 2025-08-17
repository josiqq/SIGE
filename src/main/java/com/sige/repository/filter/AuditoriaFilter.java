package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Tabla;
import com.sige.model.Tipo;
import com.sige.model.Usuario;

public class AuditoriaFilter {
   public Tabla tabla;
   public Usuario usuario;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaDesde;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaHasta;
   public Tipo tipo;

   public Tabla getTabla() {
      return this.tabla;
   }

   public void setTabla(Tabla tabla) {
      this.tabla = tabla;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public LocalDate getFechaDesde() {
      return this.fechaDesde;
   }

   public void setFechaDesde(LocalDate fechaDesde) {
      this.fechaDesde = fechaDesde;
   }

   public LocalDate getFechaHasta() {
      return this.fechaHasta;
   }

   public void setFechaHasta(LocalDate fechaHasta) {
      this.fechaHasta = fechaHasta;
   }

   public Tipo getTipo() {
      return this.tipo;
   }

   public void setTipo(Tipo tipo) {
      this.tipo = tipo;
   }
}
