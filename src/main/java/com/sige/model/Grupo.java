package com.sige.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Grupo {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @NotBlank(
      message = "Debe informar el nombre del grupo!"
   )
   private String nombre;
   @ManyToMany
   @JoinTable(
      name = "grupo_permiso",
      joinColumns = {@JoinColumn(
         name = "id_grupo"
      )},
      inverseJoinColumns = {@JoinColumn(
         name = "id_permiso"
      )}
   )
   private List<Permiso> permisos;

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

   public List<Permiso> getPermisos() {
      return this.permisos;
   }

   public void setPermisos(List<Permiso> permisos) {
      this.permisos = permisos;
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
         Grupo other = (Grupo)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
