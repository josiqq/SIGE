package com.sige.service.event.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sige.storage.FotoStorage;

@Component
public class ClienteListener {
   @Autowired
   private FotoStorage fotoStorage;

   @EventListener(
      condition = "#event.tieneFoto() and #event.nuevaFoto"
   )
   public void clienteGuarda(ClienteGuardaEvent event) {
      System.out.println("cliente listener-----" + event.getCliente().getNombre() + "--foto-- " + event.getCliente().getFoto());
      this.fotoStorage.guardar(event.getCliente().getFoto());
   }
}
