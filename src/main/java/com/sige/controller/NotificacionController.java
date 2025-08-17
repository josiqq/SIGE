package com.sige.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sige.model.Notificacion;
import com.sige.model.Vendedor;
import com.sige.repository.Notificaciones;

@Controller
@RequestMapping({"/notificaciones"})
public class NotificacionController {
   @Autowired
   private Notificaciones notificaciones;

   @GetMapping({"/js/getNotificacionesByVendedor"})
   @ResponseBody
   public List<Notificacion> getNotificacionByVendedor(Vendedor vendedor) {
      return this.notificaciones.getNotificacionByVendedor(vendedor);
   }
}
