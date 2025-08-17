package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Compra {
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
   @NotBlank(
      message = "Número de factura es obligatorio"
   )
   private String factura;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
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
   @OneToMany(
      mappedBy = "compra",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemCompra> items;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal total = BigDecimal.ZERO;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   private Precio precio;
   @Transient
   private String uuid;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   private Integer plazo = 1;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate vencimiento = LocalDate.now();

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

   public String getFactura() {
      return this.factura;
   }

   public void setFactura(String factura) {
      this.factura = factura;
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

   public List<ItemCompra> getItems() {
      return this.items;
   }

   public void setItems(List<ItemCompra> items) {
      this.items = items;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void cargarItems(List<ItemCompra> items) {
      this.items = items;
      this.items.forEach(i -> i.setCompra(this));
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id);
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public Integer getPlazo() {
      return this.plazo;
   }

   public void setPlazo(Integer plazo) {
      this.plazo = plazo;
   }

   public LocalDate getVencimiento() {
      return this.vencimiento;
   }

   public void setVencimiento(LocalDate vencimiento) {
      this.vencimiento = vencimiento;
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
         Compra other = (Compra)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
