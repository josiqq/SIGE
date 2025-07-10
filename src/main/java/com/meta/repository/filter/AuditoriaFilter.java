package com.meta.repository.filter;

import com.meta.modelo.Tabla;
import com.meta.modelo.Tipo;
import com.meta.modelo.Usuario;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

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
