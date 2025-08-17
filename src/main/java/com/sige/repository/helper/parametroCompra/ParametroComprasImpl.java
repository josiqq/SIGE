package com.sige.repository.helper.parametroCompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ParametroCompra;

public class ParametroComprasImpl implements ParametroComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public ParametroCompra getParametroCompra() {
      return (ParametroCompra)this.manager.createQuery("from ParametroCompra", ParametroCompra.class).getSingleResult();
   }
}
