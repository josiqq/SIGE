package com.sige.repository.helper.parametroVenta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ParametroVenta;

public class ParametroVentasImpl implements ParametroVentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public ParametroVenta buscarParametroVenta() {
      return (ParametroVenta)this.manager.createQuery("from ParametroVenta", ParametroVenta.class).getSingleResult();
   }
}
