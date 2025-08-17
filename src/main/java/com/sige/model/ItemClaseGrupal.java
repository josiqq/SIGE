package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemClaseGrupal {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_cliente"
   )
   private Cliente cliente;
   @ManyToOne
   @JoinColumn(
      name = "id_clase_grupal"
   )
   private ClaseGrupal claseGrupal;

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

   public ClaseGrupal getClaseGrupal() {
      return this.claseGrupal;
   }

   public void setClaseGrupal(ClaseGrupal claseGrupal) {
      this.claseGrupal = claseGrupal;
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
         ItemClaseGrupal other = (ItemClaseGrupal)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
