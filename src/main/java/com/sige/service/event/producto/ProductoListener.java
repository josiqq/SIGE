package com.sige.service.event.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sige.storage.FotoStorage;

@Component
public class ProductoListener {
   @Autowired
   private FotoStorage fotoStorage;

   @EventListener(
      condition = "#event.tieneFoto() and #event.nuevaFoto"
   )
   public void ProductoGuarda(ProductoGuardaEvent event) {
      System.out.println("producto listener-----" + event.getProducto().getNombre() + "--foto-- " + event.getProducto().getFoto());
      this.fotoStorage.guardar(event.getProducto().getFoto());
   }
}
