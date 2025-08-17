package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Planilla {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal total;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal totalRendido;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal diferencia;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   @NotNull(
      message = "Debe informar la cuenta!"
   )
   private Cuenta cuenta;
   private boolean cerrado;
   @OneToMany(
      mappedBy = "planilla",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemPlanilla> itemPlanillas = new ArrayList<>();
   @OneToMany(
      mappedBy = "planilla",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemPlanillaRendicion> itemPlanillaRendiciones = new ArrayList<>();
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   @Transient
   private String uuid;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaCierre = LocalDate.now();
   @Transient
   private boolean reAbrir;

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

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public BigDecimal getTotalRendido() {
      return this.totalRendido;
   }

   public void setTotalRendido(BigDecimal totalRendido) {
      this.totalRendido = totalRendido;
   }

   public BigDecimal getDiferencia() {
      return this.diferencia;
   }

   public void setDiferencia(BigDecimal diferencia) {
      this.diferencia = diferencia;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public boolean isCerrado() {
      return this.cerrado;
   }

   public void setCerrado(boolean cerrado) {
      this.cerrado = cerrado;
   }

   public List<ItemPlanilla> getItemPlanillas() {
      return this.itemPlanillas;
   }

   public void setItemPlanillas(List<ItemPlanilla> itemPlanillas) {
      this.itemPlanillas = itemPlanillas;
   }

   public void cargarItemPlanilla(List<ItemPlanilla> itemPlanillas) {
      this.itemPlanillas = itemPlanillas;
      this.itemPlanillas.forEach(i -> i.setPlanilla(this));
   }

   public void cargarItemPlanillaRendicion(List<ItemPlanillaRendicion> itemPlanillaRendiciones) {
      this.itemPlanillaRendiciones = itemPlanillaRendiciones;
      this.itemPlanillaRendiciones.forEach(i -> i.setPlanilla(this));
   }

   public List<ItemPlanillaRendicion> getItemPlanillaRendiciones() {
      return this.itemPlanillaRendiciones;
   }

   public void setItemPlanillaRendiciones(List<ItemPlanillaRendicion> itemPlanillaRendiciones) {
      this.itemPlanillaRendiciones = itemPlanillaRendiciones;
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

   public LocalDate getFechaCierre() {
      return this.fechaCierre;
   }

   public void setFechaCierre(LocalDate fechaCierre) {
      this.fechaCierre = fechaCierre;
   }

   public boolean isReAbrir() {
      return this.reAbrir;
   }

   public void setReAbrir(boolean reAbrir) {
      this.reAbrir = reAbrir;
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
         Planilla other = (Planilla)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
