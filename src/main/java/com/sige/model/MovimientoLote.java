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
public class MovimientoLote {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimiento;
   private String observacion;
   private Long registro;
   @ManyToOne
   @JoinColumn(
      name = "id_item_ajuste_lote"
   )
   private ItemAjusteLote itemAjsuteLote;
   private BigDecimal entrada;
   private BigDecimal salida;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   private Deposito deposito;
   private String nroLote;
   @ManyToOne
   @JoinColumn(
      name = "id_item_venta_lote"
   )
   private ItemVentaLote itemVentaLote;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public Long getRegistro() {
      return this.registro;
   }

   public void setRegistro(Long registro) {
      this.registro = registro;
   }

   public ItemAjusteLote getItemAjsuteLote() {
      return this.itemAjsuteLote;
   }

   public void setItemAjsuteLote(ItemAjusteLote itemAjsuteLote) {
      this.itemAjsuteLote = itemAjsuteLote;
   }

   public BigDecimal getEntrada() {
      return this.entrada;
   }

   public void setEntrada(BigDecimal entrada) {
      this.entrada = entrada;
   }

   public BigDecimal getSalida() {
      return this.salida;
   }

   public void setSalida(BigDecimal salida) {
      this.salida = salida;
   }

   public LocalDate getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(LocalDate vencimiento) {
      this.vencimiento = vencimiento;
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

   public ItemVentaLote getItemVentaLote() {
      return this.itemVentaLote;
   }

   public void setItemVentaLote(ItemVentaLote itemVentaLote) {
      this.itemVentaLote = itemVentaLote;
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
         MovimientoLote other = (MovimientoLote)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
