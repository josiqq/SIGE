package com.sige.repository.helper.notificacion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.model.Notificacion;
import com.sige.model.Vendedor;

public class NotificacionesImpl implements NotificacionesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Notificacion> getNotificacionByVendedor(Vendedor vendedor) {
      String sql = "from Notificacion where vendedor = :vendedor and autorizado = false and rechazado = false";
      return this.manager.createQuery(sql, Notificacion.class).setParameter("vendedor", vendedor).getResultList();
   }

   @Override
   public Notificacion getByVendedor(Vendedor vendedor) {
      String sql = "from Notificacion where vendedor = :vendedor ";
      return (Notificacion)this.manager.createQuery(sql, Notificacion.class).setParameter("vendedor", vendedor).getSingleResult();
   }

   @Transactional
   @Override
   public void modificarNotificacion(Notificacion notificacion) {
      String sql = "update notificacion set autorizado = :autorizado ,rechazado = :rechazado , precio = :precio where id = :id";
      this.manager
         .createNativeQuery(sql)
         .setParameter("autorizado", notificacion.isAutorizado())
         .setParameter("precio", notificacion.getPrecio())
         .setParameter("id", notificacion.getId())
         .setParameter("rechazado", notificacion.isRechazado())
         .executeUpdate();
   }
}
