package com.sige.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
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
public class Cobro {
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
      message = "Debe informar el cliente!"
   )
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(
      name = "id_cajero"
   )
   private Cajero cajero;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   @JsonFormat(
      shape = Shape.STRING,
      pattern = "dd/MM/yyyy"
   )
   @NotNull(
      message = "Debe informar la fecha !"
   )
   private LocalDate fecha = LocalDate.now();
   @OneToMany(
      mappedBy = "cobro",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   private List<ItemCobro> itemsCobros = new ArrayList<>();
   @OneToMany(
      mappedBy = "cobro",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemCobroImporte> itemsCobrosImportes = new ArrayList<>();
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   @Transient
   private String uuid;
   private BigDecimal total = BigDecimal.ZERO;
   @Transient
   private BigDecimal cobrado = BigDecimal.ZERO;
   @Transient
   private BigDecimal saldo = BigDecimal.ZERO;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;
   @Transient
   private Condicion condicion;
   @ManyToOne
   @JoinColumn(
      name = "id_planilla"
   )
   private Planilla planilla;
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

   public Cliente getCliente() {
      return this.cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Cajero getCajero() {
      return this.cajero;
   }

   public void setCajero(Cajero cajero) {
      this.cajero = cajero;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public List<ItemCobro> getItemsCobros() {
      return this.itemsCobros;
   }

   public void setItemsCobros(List<ItemCobro> itemsCobros) {
      this.itemsCobros = itemsCobros;
   }

   public void cargarItemCobro(List<ItemCobro> itemsCobros) {
      this.itemsCobros = itemsCobros;
      this.itemsCobros.forEach(i -> i.setCobro(this));
   }

   public List<ItemCobroImporte> getItemsCobrosImportes() {
      return this.itemsCobrosImportes;
   }

   public void setItemsCobrosImportes(List<ItemCobroImporte> itemsCobrosImportes) {
      this.itemsCobrosImportes = itemsCobrosImportes;
   }

   public void cargarItemCobroImporte(List<ItemCobroImporte> itemsCobrosImportes) {
      this.itemsCobrosImportes = itemsCobrosImportes;
      this.itemsCobrosImportes.forEach(i -> i.setCobro(this));
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void cargarCliente() {
      ItemCobro itemCobro = this.getItemsCobros().stream().findFirst().orElse(null);
      this.cliente = itemCobro.getVenta().getCliente();
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getCobrado() {
      return this.cobrado;
   }

   public void setCobrado(BigDecimal cobrado) {
      this.cobrado = cobrado;
   }

   public BigDecimal getSaldo() {
      return this.saldo;
   }

   public void setSaldo(BigDecimal saldo) {
      this.saldo = saldo;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public Condicion getCondicion() {
      return this.condicion;
   }

   public void setCondicion(Condicion condicion) {
      this.condicion = condicion;
   }

   public Planilla getPlanilla() {
      return this.planilla;
   }

   public void setPlanilla(Planilla planilla) {
      this.planilla = planilla;
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
         Cobro other = (Cobro)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
