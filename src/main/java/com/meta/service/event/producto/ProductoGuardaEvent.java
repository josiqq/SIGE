package com.meta.service.event.producto;

import com.meta.modelo.Producto;
import org.springframework.util.StringUtils;

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
