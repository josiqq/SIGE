package com.meta.controller;

import com.meta.modelo.Notificacion;
import com.meta.repository.Notificaciones;
import com.meta.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

@Controller
public class WebSocketController {
   @Autowired
   private Notificaciones notificaciones;
   @Autowired
   private NotificacionService notificacionService;

   @MessageMapping({"/notificaciones"})
   @SendTo({"/topic/notificaciones"})
   public Notificacion getNotificacion(@RequestBody Notificacion notificacion) {
      Notificacion notificacionRetorno = new Notificacion();
      this.notificacionService.guardar(notificacion);
      if (notificacion.getMensaje() != null && !StringUtils.isEmpty(notificacion.getMensaje())) {
         notificacionRetorno = (Notificacion)this.notificaciones.findById(notificacion.getId()).orElse(null);
      }

      return notificacionRetorno;
   }

   @MessageMapping({"/notificaciones/modificar"})
   @SendTo({"/topic/notificaciones/modificar"})
   public Notificacion modificarNotificacion(@RequestBody Notificacion notificacion) {
      new Notificacion();
      return this.notificacionService.modificar(notificacion);
   }
}
