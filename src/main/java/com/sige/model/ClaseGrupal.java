package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ClaseGrupal {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre de la clase!"
   )
   private String nombre;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha = LocalDate.now();
   @NotNull(
      message = "Debe informar la hora inicial!"
   )
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime horaDesde;
   @NotNull(
      message = "Debe informar la hora final!"
   )
   @DateTimeFormat(
      pattern = "HH:mm"
   )
   private LocalTime horaHasta;
   @ManyToOne
   @JoinColumn(
      name = "id_instructor"
   )
   private Instructor instructor;
   @Transient
   private String uuid;
   @OneToMany(
      mappedBy = "claseGrupal",
      cascade = {CascadeType.ALL},
      orphanRemoval = true
   )
   @JsonIgnore
   List<ItemClaseGrupal> items = new ArrayList<>();

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

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public LocalTime getHoraDesde() {
      return this.horaDesde;
   }

   public void setHoraDesde(LocalTime horaDesde) {
      this.horaDesde = horaDesde;
   }

   public LocalTime getHoraHasta() {
      return this.horaHasta;
   }

   public void setHoraHasta(LocalTime horaHasta) {
      this.horaHasta = horaHasta;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void cargarCalseGrupal(List<ItemClaseGrupal> items) {
      this.items = items;
      this.items.forEach(i -> i.setClaseGrupal(this));
   }

   public List<ItemClaseGrupal> getItems() {
      return this.items;
   }

   public void setItems(List<ItemClaseGrupal> items) {
      this.items = items;
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
         ClaseGrupal other = (ClaseGrupal)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
