package com.sige.model;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemCobroImporte {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal importe;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal importems;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal importeCobrado;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal vuelto;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal vueltoms;
   @ManyToOne
   @JoinColumn(
      name = "id_cuenta"
   )
   private Cuenta cuenta;
   @ManyToOne
   @JoinColumn(
      name = "id_cobro"
   )
   private Cobro cobro;
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
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_vuelto"
   )
   private Moneda monedaVuelto;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal valorCotizacion;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal cotizacionVuelto;
   @ManyToOne
   @JoinColumn(
      name = "id_banco"
   )
   private Banco banco;
   private String boleta;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }

   public BigDecimal getImporteCobrado() {
      return this.importeCobrado;
   }

   public void setImporteCobrado(BigDecimal importeCobrado) {
      this.importeCobrado = importeCobrado;
   }

   public BigDecimal getVuelto() {
      return this.vuelto;
   }

   public void setVuelto(BigDecimal vuelto) {
      this.vuelto = vuelto;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public Cobro getCobro() {
      return this.cobro;
   }

   public void setCobro(Cobro cobro) {
      this.cobro = cobro;
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

   public Moneda getMonedaVuelto() {
      return this.monedaVuelto;
   }

   public void setMonedaVuelto(Moneda monedaVuelto) {
      this.monedaVuelto = monedaVuelto;
   }

   public BigDecimal getValorCotizacion() {
      return this.valorCotizacion;
   }

   public void setValorCotizacion(BigDecimal valorCotizacion) {
      this.valorCotizacion = valorCotizacion;
   }

   public BigDecimal getCotizacionVuelto() {
      return this.cotizacionVuelto;
   }

   public void setCotizacionVuelto(BigDecimal cotizacionVuelto) {
      this.cotizacionVuelto = cotizacionVuelto;
   }

   public BigDecimal getImportems() {
      return this.importems;
   }

   public void setImportems(BigDecimal importems) {
      this.importems = importems;
   }

   public BigDecimal getVueltoms() {
      return this.vueltoms;
   }

   public void setVueltoms(BigDecimal vueltoms) {
      this.vueltoms = vueltoms;
   }

   public Banco getBanco() {
      return this.banco;
   }

   public void setBanco(Banco banco) {
      this.banco = banco;
   }

   public String getBoleta() {
      return this.boleta;
   }

   public void setBoleta(String boleta) {
      this.boleta = boleta;
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
         ItemCobroImporte other = (ItemCobroImporte)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
