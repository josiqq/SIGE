package com.sige.service.event.producto;

import org.springframework.util.StringUtils;

import com.sige.model.Producto;

public class ProductoGuardaEvent {
   private Producto producto;

   public ProductoGuardaEvent(Producto producto) {
      this.producto = producto;
   }

   public Producto getProducto() {
      return this.producto;
   }

   public boolean tieneFoto() {
      return !StringUtils.isEmpty(this.producto.getFoto());
   }

   public boolean isNuevaFoto() {
      return this.producto.isNuevaFoto();
   }
}
