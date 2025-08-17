package com.sige.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Notificacion;
import com.sige.repository.Notificaciones;

@Service
public class NotificacionService {
   @Autowired
   private Notificaciones notificaciones;

   public void guardar(Notificacion notificacion) {
      this.notificaciones.save(notificacion);
   }

   public Notificacion modificar(Notificacion notificacion) {
      this.notificaciones.modificarNotificacion(notificacion);
      this.notificaciones.flush();
      return (Notificacion)this.notificaciones.findById(notificacion.getId()).orElse(null);
   }

   public List<Notificacion> getAllNotificaciones() {
      return this.notificaciones.findAll();
   }
}
