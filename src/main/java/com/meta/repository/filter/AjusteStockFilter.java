package com.meta.repository.filter;

import com.meta.modelo.Deposito;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class AjusteStockFilter {
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaDesde;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaHasta;
   private boolean confirmado;
   private Deposito deposito;

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

   public boolean isConfirmado() {
      return this.confirmado;
   }

   public void setConfirmado(boolean confirmado) {
      this.confirmado = confirmado;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }
}
