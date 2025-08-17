package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class Venta {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   @NotNull(
      message = "Debe informar cliente!"
   )
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   @NotNull(
      message = "Debe informar el deposito !"
   )
   private Deposito deposito;
   private BigDecimal total = BigDecimal.ZERO;
   @NotNull(
      message = "Debe informar la fecha"
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
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   @NotNull(
      message = "Debe informar el Vendedor!"
   )
   private Vendedor vendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   @NotNull(
      message = "Debe informar el precio !"
   )
   private Precio precio;
   @OneToMany(
      mappedBy = "venta",
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemVenta> items = new ArrayList<>();
   private boolean factura;
   @ManyToOne
   @JoinColumn(
      name = "id_timbrado"
   )
   private Timbrado timbrado;
   private Integer nroFactura;
   private CondicionVenta condicionVenta;
   private boolean impreso;
   @ManyToOne
   @JoinColumn(
      name = "id_consorcio"
   )
   private Consorcio consorcio;
   @Transient
   private String uuid;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   @Transient
   private Condicion condicion;
   public boolean nc;
   private Long ncVenta;
   @OneToMany(
      mappedBy = "venta",
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemVentaLote> itemsLote = new ArrayList<>();

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
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

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public List<ItemVenta> getItems() {
      return this.items;
   }

   public void setItems(List<ItemVenta> items) {
      this.items = items;
   }

   public void cargarItem(List<ItemVenta> items) {
      this.items = items;
      this.items.forEach(i -> i.setVenta(this));
   }

   public void cargarItemsLotes(List<ItemVentaLote> itemsLote) {
      this.itemsLote = itemsLote;
      this.itemsLote.forEach(i -> i.setVenta(this));
   }

   public void calcularTotal() {
      BigDecimal totalItem = this.getItems().stream().map(ItemVenta::getSubTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
      this.total = totalItem;
   }

   public boolean isFactura() {
      return this.factura;
   }

   public void setFactura(boolean factura) {
      this.factura = factura;
   }

   public Timbrado getTimbrado() {
      return this.timbrado;
   }

   public void setTimbrado(Timbrado timbrado) {
      this.timbrado = timbrado;
   }

   public Integer getNroFactura() {
      return this.nroFactura;
   }

   public void setNroFactura(Integer nroFactura) {
      this.nroFactura = nroFactura;
   }

   public CondicionVenta getCondicionVenta() {
      return this.condicionVenta;
   }

   public void setCondicionVenta(CondicionVenta condicionVenta) {
      this.condicionVenta = condicionVenta;
   }

   public boolean isImpreso() {
      return this.impreso;
   }

   public void setImpreso(boolean impreso) {
      this.impreso = impreso;
   }

   public Consorcio getConsorcio() {
      return this.consorcio;
   }

   public void setConsorcio(Consorcio consorcio) {
      this.consorcio = consorcio;
   }

   public Moneda getMoneda() {
      return this.moneda;
   }

   public void setMoneda(Moneda moneda) {
      this.moneda = moneda;
   }

   public Condicion getCondicion() {
      return this.condicion;
   }

   public void setCondicion(Condicion condicion) {
      this.condicion = condicion;
   }

   public boolean isNc() {
      return this.nc;
   }

   public void setNc(boolean nc) {
      this.nc = nc;
   }

   public Long getNcVenta() {
      return this.ncVenta;
   }

   public void setNcVenta(Long ncVenta) {
      this.ncVenta = ncVenta;
   }

   public List<ItemVentaLote> getItemsLote() {
      return this.itemsLote;
   }

   public void setItemsLote(List<ItemVentaLote> itemsLote) {
      this.itemsLote = itemsLote;
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
         Venta other = (Venta)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
