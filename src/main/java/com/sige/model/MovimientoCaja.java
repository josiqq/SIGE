package com.sige.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class MovimientoCaja {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private Long id;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fecha;
   @JoinColumn
   @ManyToOne
   private Membresia membresia;
   @JoinColumn
   @ManyToOne
   private Gasto gasto;
   @JoinColumn
   @ManyToOne
   private Cuenta cuenta;
   private String observacion;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal debito;
   @Column(
      precision = 35,
      scale = 16
   )
   private BigDecimal credito;
   @JoinColumn(
      name = "id_pago_instructor"
   )
   @ManyToOne
   private PagoInstructor pagoInstructor;
   @ManyToOne
   @JoinColumn(
      name = "id_cobro_servicio"
   )
   private CobroServicio cobroServicio;
   @ManyToOne
   @JoinColumn(
      name = "id_pago_vendedor"
   )
   private PagoVendedor pagoVendedor;
   @ManyToOne
   @JoinColumn(
      name = "id_transferencia_efectivo"
   )
   private TransferenciaEfectivo transferenciaEfectivo;
   @ManyToOne
   @JoinColumn(
      name = "id_anticipo_cliente"
   )
   private AnticipoCliente anticipoCliente;
   @ManyToOne
   @JoinColumn(
      name = "id_item_cobro_importe"
   )
   private ItemCobroImporte itemCobroImporte;
   @ManyToOne
   @JoinColumn(
      name = "id_item_pago_importe"
   )
   private ItemPagoImporte itemPagoImporte;
   @ManyToOne
   @JoinColumn(
      name = "id_item_consorcio_importe"
   )
   private ItemConsorcioImporte itemConsorcioImporte;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_cuenta"
   )
   private Moneda monedaCuenta;
   @ManyToOne
   @JoinColumn(
      name = "id_moneda_cobro"
   )
   private Moneda monedaCobro;
   private BigDecimal valorCotizacion;
   @ManyToOne
   @JoinColumn(
      name = "id_item_planilla"
   )
   private ItemPlanilla itemPlanilla;
   @ManyToOne
   @JoinColumn(
      name = "id_item_planilla_rendicion"
   )
   private ItemPlanillaRendicion itemPlanilla_rendicion;

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDate getFecha() {
      return this.fecha;
   }

   public void setFecha(LocalDate fecha) {
      this.fecha = fecha;
   }

   public Membresia getMembresia() {
      return this.membresia;
   }

   public void setMembresia(Membresia membresia) {
      this.membresia = membresia;
   }

   public Gasto getGasto() {
      return this.gasto;
   }

   public void setGasto(Gasto gasto) {
      this.gasto = gasto;
   }

   public Cuenta getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(Cuenta cuenta) {
      this.cuenta = cuenta;
   }

   public String getObservacion() {
      return this.observacion;
   }

   public void setObservacion(String observacion) {
      this.observacion = observacion;
   }

   public BigDecimal getDebito() {
      return this.debito;
   }

   public void setDebito(BigDecimal debito) {
      this.debito = debito;
   }

   public BigDecimal getCredito() {
      return this.credito;
   }

   public void setCredito(BigDecimal credito) {
      this.credito = credito;
   }

   public PagoInstructor getPagoInstructor() {
      return this.pagoInstructor;
   }

   public void setPagoInstructor(PagoInstructor pagoInstructor) {
      this.pagoInstructor = pagoInstructor;
   }

   public CobroServicio getCobroServicio() {
      return this.cobroServicio;
   }

   public void setCobroServicio(CobroServicio cobroServicio) {
      this.cobroServicio = cobroServicio;
   }

   public PagoVendedor getPagoVendedor() {
      return this.pagoVendedor;
   }

   public void setPagoVendedor(PagoVendedor pagoVendedor) {
      this.pagoVendedor = pagoVendedor;
   }

   public TransferenciaEfectivo getTransferenciaEfectivo() {
      return this.transferenciaEfectivo;
   }

   public void setTransferenciaEfectivo(TransferenciaEfectivo transferenciaEfectivo) {
      this.transferenciaEfectivo = transferenciaEfectivo;
   }

   public AnticipoCliente getAnticipoCliente() {
      return this.anticipoCliente;
   }

   public void setAnticipoCliente(AnticipoCliente anticipoCliente) {
      this.anticipoCliente = anticipoCliente;
   }

   public ItemCobroImporte getItemCobroImporte() {
      return this.itemCobroImporte;
   }

   public void setItemCobroImporte(ItemCobroImporte itemCobroImporte) {
      this.itemCobroImporte = itemCobroImporte;
   }

   public ItemPagoImporte getItemPagoImporte() {
      return this.itemPagoImporte;
   }

   public void setItemPagoImporte(ItemPagoImporte itemPagoImporte) {
      this.itemPagoImporte = itemPagoImporte;
   }

   public ItemConsorcioImporte getItemConsorcioImporte() {
      return this.itemConsorcioImporte;
   }

   public void setItemConsorcioImporte(ItemConsorcioImporte itemConsorcioImporte) {
      this.itemConsorcioImporte = itemConsorcioImporte;
   }

   public Moneda getMonedaCuenta() {
      return this.monedaCuenta;
   }

   public void setMonedaCuenta(Moneda monedaCuenta) {
      this.monedaCuenta = monedaCuenta;
   }

   public Moneda getMonedaCobro() {
      return this.monedaCobro;
   }

   public void setMonedaCobro(Moneda monedaCobro) {
      this.monedaCobro = monedaCobro;
   }

   public BigDecimal getValorCotizacion() {
      return this.valorCotizacion;
   }

   public void setValorCotizacion(BigDecimal valorCotizacion) {
      this.valorCotizacion = valorCotizacion;
   }

   public ItemPlanilla getItemPlanilla() {
      return this.itemPlanilla;
   }

   public void setItemPlanilla(ItemPlanilla itemPlanilla) {
      this.itemPlanilla = itemPlanilla;
   }

   public ItemPlanillaRendicion getItemPlanilla_rendicion() {
      return this.itemPlanilla_rendicion;
   }

   public void setItemPlanilla_rendicion(ItemPlanillaRendicion itemPlanilla_rendicion) {
      this.itemPlanilla_rendicion = itemPlanilla_rendicion;
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
         MovimientoCaja other = (MovimientoCaja)obj;
         return Objects.equals(this.id, other.id);
      }
   }
}
