package com.sige.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Notificacion {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalTime hora;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   private String uuid;
   private String mensaje;
   private boolean autorizado;
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor"
   )
   private Vendedor vendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_producto"
   )
   private Producto producto;
   private BigDecimal precio;
   private BigDecimal precioMinimo;
   @ManyToOne
   @JoinColumn(
      name = "id_vendedor_solicitante"
   )
   private Vendedor vendedorSolicitante;
   private BigDecimal precioLista;
   private boolean rechazado;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalTime getHora() {
      return this.hora;
   }

   public void setHora(LocalTime hora) {
      this.hora = hora;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public String getUuid() {
      return this.uuid;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public String getMensaje() {
      return this.mensaje;
   }

   public void setMensaje(String mensaje) {
      this.mensaje = mensaje;
   }

   public boolean isAutorizado() {
      return this.autorizado;
   }

   public void setAutorizado(boolean autorizado) {
      this.autorizado = autorizado;
   }

   public Vendedor getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(Vendedor vendedor) {
      this.vendedor = vendedor;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public BigDecimal getPrecioMinimo() {
      return this.precioMinimo;
   }

   public void setPrecioMinimo(BigDecimal precioMinimo) {
      this.precioMinimo = precioMinimo;
   }

   public Vendedor getVendedorSolicitante() {
      return this.vendedorSolicitante;
   }

   public void setVendedorSolicitante(Vendedor vendedorSolicitante) {
      this.vendedorSolicitante = vendedorSolicitante;
   }

   public BigDecimal getPrecioLista() {
      return this.precioLista;
   }

   public void setPrecioLista(BigDecimal precioLista) {
      this.precioLista = precioLista;
   }

   public boolean isRechazado() {
      return this.rechazado;
   }

   public void setRechazado(boolean rechazado) {
      this.rechazado = rechazado;
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
         Notificacion other = (Notificacion)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
