package com.meta.repository.filter;

import com.meta.modelo.Deposito;
import com.meta.modelo.Producto;

public class StockFilter {
   private Producto producto;
   private Deposito deposito;

   public Producto getProducto() {
      return this.producto;
   }

   public void setProducto(Producto producto) {
      this.producto = producto;
   }

   public Deposito getDeposito() {
      return this.deposito;
   }

   public void setDeposito(Deposito deposito) {
      this.deposito = deposito;
   }
}
