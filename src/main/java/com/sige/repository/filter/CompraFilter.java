package com.sige.repository.filter;

import java.time.LocalDate;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Proveedor;

public class CompraFilter {
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaIni;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaFin;
   private Integer limite = 10;
   private Proveedor proveedor;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDate getFechaIni() {
      return this.fechaIni;
   }

   public void setFechaIni(LocalDate fechaIni) {
      this.fechaIni = fechaIni;
   }

   public LocalDate getFechaFin() {
      return this.fechaFin;
   }

   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }

   public Integer getLimite() {
      return this.limite;
   }

   public void setLimite(Integer limite) {
      this.limite = limite;
   }

   public Proveedor getProveedor() {
      return this.proveedor;
   }

   public void setProveedor(Proveedor proveedor) {
      this.proveedor = proveedor;
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
         CompraFilter other = (CompraFilter)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
