package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParametroCompra {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   private Deposito deposito;
   @ManyToOne
   @JoinColumn(
      name = "id_precio"
   )
   private Precio precio;
   private boolean agregarPrecio;
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
   }

   public boolean isAgregarPrecio() {
      return this.agregarPrecio;
   }

   public void setAgregarPrecio(boolean agregarPrecio) {
      this.agregarPrecio = agregarPrecio;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
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
         ParametroCompra other = (ParametroCompra)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
