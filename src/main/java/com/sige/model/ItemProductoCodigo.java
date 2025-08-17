package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemProductoCodigo {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   private String codigoAlternativo;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCodigoAlternativo() {
      return this.codigoAlternativo;
   }

   public void setCodigoAlternativo(String codigoAlternativo) {
      this.codigoAlternativo = codigoAlternativo;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
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
         ItemProductoCodigo other = (ItemProductoCodigo)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
