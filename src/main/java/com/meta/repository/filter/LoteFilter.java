package com.meta.repository.filter;

import com.meta.modelo.Deposito;
import com.meta.modelo.Producto;
import java.time.LocalDate;

public class LoteFilter {
   private Producto producto;
   private Deposito deposito;
   private LocalDate fechaDesde;
   private LocalDate fechaHasta;
   private String nroLote;

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
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

   public String getNroLote() {
      return this.nroLote;
   }

   public void setNroLote(String nroLote) {
      this.nroLote = nroLote;
   }
}
