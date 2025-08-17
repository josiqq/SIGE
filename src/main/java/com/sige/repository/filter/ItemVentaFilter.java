package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Cliente;
import com.sige.model.Vendedor;

public class ItemVentaFilter {
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta = LocalDate.now();
   private Cliente cliente;
   private Vendedor vendedor;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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
}
