package com.sige.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.util.StringUtils;

import com.sige.model.Notificacion;
import com.sige.repository.Notificaciones;
import com.sige.service.NotificacionService;

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
