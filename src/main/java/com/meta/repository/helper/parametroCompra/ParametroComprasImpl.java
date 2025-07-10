package com.meta.repository.helper.parametroCompra;

import com.meta.modelo.ParametroCompra;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ParametroComprasImpl implements ParametroComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public ParametroCompra getParametroCompra() {
      return (ParametroCompra)this.manager.createQuery("from ParametroCompra", ParametroCompra.class).getSingleResult();
   }
}
