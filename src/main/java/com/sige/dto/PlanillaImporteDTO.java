package com.sige.dto;

import java.math.BigDecimal;

public class PlanillaImporteDTO {
   private String condicion;
   private String moneda;
   private BigDecimal cobro;
   private BigDecimal apertura;
   private BigDecimal masVuelto;
   private BigDecimal menosVuelto;
   private BigDecimal transCredi;
   private BigDecimal transDebi;
   private BigDecimal cotizacion;

   public String getCondicion() {
      return this.condicion;
   }

   public void setCondicion(String condicion) {
      this.condicion = condicion;
   }

   public String getMoneda() {
      return this.moneda;
   }

   public void setMoneda(String moneda) {
      this.moneda = moneda;
   }

   public BigDecimal getCobro() {
      return this.cobro;
   }

   public void setCobro(BigDecimal cobro) {
      this.cobro = cobro;
   }

   public BigDecimal getApertura() {
      return this.apertura;
   }

   public void setApertura(BigDecimal apertura) {
      this.apertura = apertura;
   }

   public BigDecimal getMasVuelto() {
      return this.masVuelto;
   }

   public void setMasVuelto(BigDecimal masVuelto) {
      this.masVuelto = masVuelto;
   }

   public BigDecimal getMenosVuelto() {
      return this.menosVuelto;
   }

   public void setMenosVuelto(BigDecimal menosVuelto) {
      this.menosVuelto = menosVuelto;
   }

   public BigDecimal getTransCredi() {
      return this.transCredi;
   }

   public void setTransCredi(BigDecimal transCredi) {
      this.transCredi = transCredi;
   }

   public BigDecimal getTransDebi() {
      return this.transDebi;
   }

   public void setTransDebi(BigDecimal transDebi) {
      this.transDebi = transDebi;
   }

   public BigDecimal getCotizacion() {
      return this.cotizacion;
   }

   public void setCotizacion(BigDecimal cotizacion) {
      this.cotizacion = cotizacion;
   }
}
