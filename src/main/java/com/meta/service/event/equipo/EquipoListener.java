package com.meta.service.event.equipo;

import com.meta.storage.FotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EquipoListener {
   @Autowired
   private FotoStorage fotoStorage;

   @EventListener(
      condition = "#event.tieneFoto() and #event.nuevaFoto"
   )
   public void clienteGuarda(EquipoGuardaEvent event) {
      System.out.println("cliente listener-----" + event.getEquipo().getNombre() + "--foto-- " + event.getEquipo().getFoto());
      this.fotoStorage.guardar(event.getEquipo().getFoto());
   }
}
