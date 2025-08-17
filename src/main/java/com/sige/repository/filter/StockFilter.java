package com.sige.repository.filter;

import com.sige.model.Deposito;
import com.sige.model.Producto;

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
