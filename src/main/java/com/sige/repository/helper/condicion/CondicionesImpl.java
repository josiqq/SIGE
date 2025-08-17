package com.sige.repository.helper.condicion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Condicion;

public class CondicionesImpl implements CondicionesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Condicion getCondicionById(Long id) {
      String sql = "from Condicion where id = :id";

      try {
         return (Condicion)this.manager.createQuery(sql, Condicion.class).setParameter("id", id).getSingleResult();
      } catch (Exception var4) {
         return new Condicion();
      }
   }

   @Override
   public List<Condicion> getCondicionDistintoEfectivo() {
      String sql = "from Condicion where condicionCobro<>0";
      return this.manager.createQuery(sql, Condicion.class).getResultList();
   }
}
