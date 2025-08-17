package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FacturaDto {
   private BigInteger id;
   private String cliente;
   private String documento;
   private String factura;
   private String fecha;
   private String condicion;
   private String vendedor;
   private String codigo;
   private String producto;
   private BigDecimal cantidad;
   private BigDecimal precio;
   private BigDecimal subTotal;
   private Integer gravado;
   private BigDecimal gravadoDies;
   private BigDecimal gravadoCinco;
   private BigDecimal impuestoDies;
   private BigDecimal impuestoCinco;
   private Integer timbrado;
   private String fechaInicio;
   private String fechaFin;
   private boolean pesable;

   public BigInteger getId() {
      return this.id;
   }

   public void setId(BigInteger id) {
      this.id = id;
   }

   public String getCliente() {
      return this.cliente;
   }

   public void setCliente(String cliente) {
      this.cliente = cliente;
   }

   public String getDocumento() {
      return this.documento;
   }

   public void setDocumento(String documento) {
      this.documento = documento;
   }

   public String getFactura() {
      return this.factura;
   }

   public void setFactura(String factura) {
      this.factura = factura;
   }

   public String getFecha() {
      return this.fecha;
   }

   public void setFecha(String fecha) {
      this.fecha = fecha;
   }

   public String getCondicion() {
      return this.condicion;
   }

   public void setCondicion(String condicion) {
      this.condicion = condicion;
   }

   public String getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(String vendedor) {
      this.vendedor = vendedor;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public String getProducto() {
      return this.producto;
   }

   public void setProducto(String producto) {
      this.producto = producto;
   }

   public BigDecimal getCantidad() {
      return this.cantidad;
   }

   public void setCantidad(BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   public BigDecimal getPrecio() {
      return this.precio;
   }

   public void setPrecio(BigDecimal precio) {
      this.precio = precio;
   }

   public BigDecimal getSubTotal() {
      return this.subTotal;
   }

   public void setSubTotal(BigDecimal subTotal) {
      this.subTotal = subTotal;
   }

   public Integer getGravado() {
      return this.gravado;
   }

   public void setGravado(Integer gravado) {
      this.gravado = gravado;
   }

   public BigDecimal getGravadoDies() {
      return this.gravadoDies;
   }

   public void setGravadoDies(BigDecimal gravadoDies) {
      this.gravadoDies = gravadoDies;
   }

   public BigDecimal getGravadoCinco() {
      return this.gravadoCinco;
   }

   public void setGravadoCinco(BigDecimal gravadoCinco) {
      this.gravadoCinco = gravadoCinco;
   }

   public BigDecimal getImpuestoDies() {
      return this.impuestoDies;
   }

   public void setImpuestoDies(BigDecimal impuestoDies) {
      this.impuestoDies = impuestoDies;
   }

   public BigDecimal getImpuestoCinco() {
      return this.impuestoCinco;
   }

   public void setImpuestoCinco(BigDecimal impuestoCinco) {
      this.impuestoCinco = impuestoCinco;
   }

   public Integer getTimbrado() {
      return this.timbrado;
   }

   public void setTimbrado(Integer timbrado) {
      this.timbrado = timbrado;
   }

   public String getFechaInicio() {
      return this.fechaInicio;
   }

   public void setFechaInicio(String fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   public String getFechaFin() {
      return this.fechaFin;
   }

   public void setFechaFin(String fechaFin) {
      this.fechaFin = fechaFin;
   }

   public boolean isPesable() {
      return this.pesable;
   }

   public void setPesable(boolean pesable) {
      this.pesable = pesable;
   }
}
