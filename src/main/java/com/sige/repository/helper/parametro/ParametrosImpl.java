package com.sige.repository.helper.parametro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Parametro;

public class ParametrosImpl implements ParametrosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Long cantParametro(Long id) {
      return (Long)this.manager.createQuery("select count(id) from Parametro where id= :id", Long.class).setParameter("id", id).getSingleResult();
   }

   @Override
   public Parametro recuperarParametro() {
      return (Parametro)this.manager.createQuery("from Parametro where id = 1", Parametro.class).getSingleResult();
   }

   @Override
   public Parametro getParametro() {
      new Parametro();

      try {
         return (Parametro)this.manager.createQuery("from Parametro", Parametro.class).getSingleResult();
      } catch (Exception var3) {
         return new Parametro();
      }
   }
}
