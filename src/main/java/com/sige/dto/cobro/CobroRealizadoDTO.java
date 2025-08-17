package com.sige.dto.cobro;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CobroRealizadoDTO {
   public BigInteger cobro;
   public String fecha;
   public String cuenta;
   public String cliente;
   public String condicion;
   public String moneda;
   public String sigla;
   public BigDecimal importe;

   public BigInteger getCobro() {
      return this.cobro;
   }

   public void setCobro(BigInteger cobro) {
      this.cobro = cobro;
   }

   public String getFecha() {
      return this.fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getCuenta() {
      return this.cuenta;
   }

   public void setCuenta(String cuenta) {
      this.cuenta = cuenta;
   }

   public String getCliente() {
      return this.cliente;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

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

   public String getSigla() {
      return this.sigla;
   }

   public void setSigla(String sigla) {
      this.sigla = sigla;
   }

   public BigDecimal getImporte() {
      return this.importe;
   }

   public void setImporte(BigDecimal importe) {
      this.importe = importe;
   }
}
