package com.sige.repository.helper.notificacion;

import java.util.List;

import com.sige.model.Notificacion;
import com.sige.model.Vendedor;

public interface NotificacionesQueries {
   List<Notificacion> getNotificacionByVendedor(Vendedor vendedor);

   Notificacion getByVendedor(Vendedor vendedor);

   void modificarNotificacion(Notificacion notificacion);
}
