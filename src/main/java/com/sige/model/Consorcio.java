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
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consorcio {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre"
   )
   private String nombre;
   @NotNull(
      message = "Debe informar la fecha inicial"
   )
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaInicio;
   @NotNull(
      message = "Debe informar la fecha final"
   )
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaFin;
   private BigDecimal montoMensual = BigDecimal.ZERO;
   private Integer meses = 0;
   @OneToMany(
      mappedBy = "consorcio",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   private List<ItemConsorcio> items = new ArrayList<>();
   private boolean terminado;
   @Transient
   private String uuid;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public LocalDate getFechaInicio() {
      return this.fechaInicio;
   }

   public void setFechaInicio(LocalDate fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   public LocalDate getFechaFin() {
      return this.fechaFin;
   }

   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }

   public BigDecimal getMontoMensual() {
      return this.montoMensual;
   }

   public void setMontoMensual(BigDecimal montoMensual) {
      this.montoMensual = montoMensual;
   }

   public Integer getMeses() {
      return this.meses;
   }

   public void setMeses(Integer meses) {
      this.meses = meses;
   }

   public List<ItemConsorcio> getItems() {
      return this.items;
   }

   public void setItems(List<ItemConsorcio> items) {
      this.items = items;
   }

   public void agregarItems(List<ItemConsorcio> items) {
      this.items = items;
      this.items.forEach(i -> i.setConsorcio(this));
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public boolean isTerminado() {
      return this.terminado;
   }

   public void setTerminado(boolean terminado) {
      this.terminado = terminado;
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
         Consorcio other = (Consorcio)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
