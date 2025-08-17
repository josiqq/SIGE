package com.sige.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "¡Debe informar el nombre!"
   )
   private String nombre;
   private String password;
   private boolean activo = true;
   @ManyToMany
   @JoinTable(
      name = "usuario_grupo",
      joinColumns = {@JoinColumn(
         name = "id_usuario"
      )},
      inverseJoinColumns = {@JoinColumn(
         name = "id_grupo"
      )}
   )
   @Size(
      min = 1,
      message = "¡Debe informar al menos un grupo!"
   )
   @JsonIgnore
   private List<Grupo> grupos;
   private boolean supervisor;
   @ManyToOne
   @JoinColumn(
      name = "id_instructor"
   )
   private Instructor instructor;
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   private Vendedor vendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_cajero"
   )
   private Cajero cajero;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public List<Grupo> getGrupos() {
      return this.grupos;
   }

   public void setGrupos(List<Grupo> grupos) {
      this.grupos = grupos;
   }

   public boolean isSupervisor() {
      return this.supervisor;
   }

   public void setSupervisor(boolean supervisor) {
      this.supervisor = supervisor;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public Cajero getCajero() {
      return this.cajero;
   }

   public void setCajero(Cajero cajero) {
      this.cajero = cajero;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
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
         Usuario other = (Usuario)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
