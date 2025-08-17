package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Deposito;
import com.sige.model.Producto;

public class AjusteLoteFilter {
   private Long id;
   private Producto producto;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private Deposito deposito;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
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

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }
}
