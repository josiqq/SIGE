package com.sige.repository.helper.moneda;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MonedasImpl implements MonedasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public int getCantDecimal(Long id) {
      return (Integer)this.manager.createQuery("Select decimales from Moneda where id = :id", Integer.class).setParameter("id", id).getSingleResult();
   }
}
