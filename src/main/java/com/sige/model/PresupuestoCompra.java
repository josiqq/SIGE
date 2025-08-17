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
public class PresupuestoCompra {
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
      message = "Debe informar el proveedor"
   )
   private Proveedor proveedor;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha"
   )
   private LocalDate fecha = LocalDate.now();
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   @NotNull(
      message = "Debe informar el deposito"
   )
   private Deposito deposito;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   @NotNull(
      message = "Debe informar la moneda"
   )
   private Moneda moneda;
   @OneToMany(
      mappedBy = "presupuestoCompra",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   public List<ItemPresupuestoCompra> items = new ArrayList<>();
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   private BigDecimal total;
   @Transient
   private String uuid;

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

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public List<ItemPresupuestoCompra> getItems() {
      return this.items;
   }

   public void setItems(List<ItemPresupuestoCompra> items) {
      this.items = items;
   }

   public void agregarItem(List<ItemPresupuestoCompra> items) {
      this.items = items;
      this.items.forEach(i -> i.setPresupuestoCompra(this));
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
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
         PresupuestoCompra other = (PresupuestoCompra)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
