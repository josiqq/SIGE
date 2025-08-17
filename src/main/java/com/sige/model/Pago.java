package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pago {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_proveedor"
   )
   @NotNull(
      message = "Debe informar el proveedor!"
   )
   private Proveedor proveedor;
   @NotNull(
      message = "Debe informar la fecha!"
   )
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   @OneToMany(
      mappedBy = "pago",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemPago> itemPagos = new ArrayList<>();
   @OneToMany(
      mappedBy = "pago",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemPagoImporte> itemPagoImportes = new ArrayList<>();
   @Transient
   private String uuid;
   private BigDecimal total = BigDecimal.ZERO;
   @Transient
   private BigDecimal pagado = BigDecimal.ZERO;
   @Transient
   private BigDecimal saldo = BigDecimal.ZERO;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Proveedor getProveedor() {
      return this.proveedor;
   }

   public void setProveedor(Proveedor proveedor) {
      this.proveedor = proveedor;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public List<ItemPago> getItemPagos() {
      return this.itemPagos;
   }

   public void setItemPagos(List<ItemPago> itemPagos) {
      this.itemPagos = itemPagos;
   }

   public List<ItemPagoImporte> getItemPagoImportes() {
      return this.itemPagoImportes;
   }

   public void setItemPagoImportes(List<ItemPagoImporte> itemPagoImportes) {
      this.itemPagoImportes = itemPagoImportes;
   }

   public void cargarPagoItem(List<ItemPago> itemPagos) {
      this.itemPagos = itemPagos;
      this.itemPagos.forEach(i -> i.setPago(this));
   }

   public void cargarPagoItemImporte(List<ItemPagoImporte> itemPagoImportes) {
      this.itemPagoImportes = itemPagoImportes;
      this.itemPagoImportes.forEach(i -> i.setPago(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getPagado() {
      return this.pagado;
   }

   public void setPagado(BigDecimal pagado) {
      this.pagado = pagado;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
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
         Pago other = (Pago)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
