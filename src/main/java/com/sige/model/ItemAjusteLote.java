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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ItemAjusteLote {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;
   private String nroLote;
   private BigDecimal cantidad;
   @ManyToOne
   @JoinColumn(
      name = "id_ajuste_lote"
   )
   private AjusteLote ajusteLote;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimiento;

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

   public String getNroLote() {
      return this.nroLote;
   }

   public void setNroLote(String nroLote) {
      this.nroLote = nroLote;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public AjusteLote getAjusteLote() {
      return this.ajusteLote;
   }

   public void setAjusteLote(AjusteLote ajusteLote) {
      this.ajusteLote = ajusteLote;
   }

   public LocalDate getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(LocalDate vencimiento) {
      this.vencimiento = vencimiento;
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
         ItemAjusteLote other = (ItemAjusteLote)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
