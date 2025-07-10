package com.meta.repository.helper.notificacion;

import com.meta.modelo.Notificacion;
import com.meta.modelo.Vendedor;
import java.util.List;

public interface NotificacionesQueries {
   List<Notificacion> getNotificacionByVendedor(Vendedor vendedor);

   Notificacion getByVendedor(Vendedor vendedor);

   void modificarNotificacion(Notificacion notificacion);
}
