package com.meta.repository.helper.parametroVenta;

import com.meta.modelo.ParametroVenta;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ParametroVentasImpl implements ParametroVentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public ParametroVenta buscarParametroVenta() {
      return (ParametroVenta)this.manager.createQuery("from ParametroVenta", ParametroVenta.class).getSingleResult();
   }
}
