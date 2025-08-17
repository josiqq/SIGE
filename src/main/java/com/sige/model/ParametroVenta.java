package com.sige.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ParametroVenta {
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
      name = "id_precio"
   )
   private Precio precio;
   @ManyToOne
   @JoinColumn(
      name = "id_deposito"
   )
   private Deposito deposito;
   @ManyToOne
   @JoinColumn(
      name = "id_usuario"
   )
   private Usuario usuario;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda"
   )
   private Moneda moneda;
   @ManyToOne
   @JoinColumn(
      name = "id_condicion"
   )
   private Condicion condicion;
   private boolean cobroVenta;
   private boolean cobroVentaMobile;
   private boolean ticketSiete;
   private boolean ticketCinco;
   private boolean ticketGenerico;

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

   public Precio getPrecio() {
      return this.precio;
   }

   public void setPrecio(Precio precio) {
      this.precio = precio;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }

   public Usuario getUsuario() {
      return this.usuario;
   }

   public void setUsuario(Usuario usuario) {
      this.usuario = usuario;
   }

   public boolean isCobroVenta() {
      return this.cobroVenta;
   }

   public void setCobroVenta(boolean cobroVenta) {
      this.cobroVenta = cobroVenta;
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

   public boolean isCobroVentaMobile() {
      return this.cobroVentaMobile;
   }

   public void setCobroVentaMobile(boolean cobroVentaMobile) {
      this.cobroVentaMobile = cobroVentaMobile;
   }

   public boolean isTicketSiete() {
      return this.ticketSiete;
   }

   public void setTicketSiete(boolean ticketSiete) {
      this.ticketSiete = ticketSiete;
   }

   public boolean isTicketCinco() {
      return this.ticketCinco;
   }

   public void setTicketCinco(boolean ticketCinco) {
      this.ticketCinco = ticketCinco;
   }

   public boolean isTicketGenerico() {
      return this.ticketGenerico;
   }

   public void setTicketGenerico(boolean ticketGenerico) {
      this.ticketGenerico = ticketGenerico;
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
         ParametroVenta other = (ParametroVenta)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
