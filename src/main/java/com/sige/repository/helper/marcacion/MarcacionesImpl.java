package com.sige.repository.helper.marcacion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.model.Marcacion;

public class MarcacionesImpl implements MarcacionesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Marcacion buscarMarcacionAbierto() {
      return (Marcacion)this.manager.createQuery("from Marcacion where terminado = 0", Marcacion.class).getSingleResult();
   }

   @Transactional
   @Override
   public void ModificarPuntoA(Integer Punto) {
      this.manager.createQuery("update Marcacion set puntoa = :punto where terminado =0").setParameter("punto", Punto).executeUpdate();
   }

   @Transactional
   @Override
   public void ModificarPuntoB(Integer Punto) {
      this.manager.createQuery("update Marcacion set puntob = :punto where terminado =0").setParameter("punto", Punto).executeUpdate();
   }

   @Override
   public Long getMaximoId() {
      String sql = "select max(id) from Marcacion";
      return (Long)this.manager.createQuery(sql, Long.class).getSingleResult();
   }

   @Override
   public Marcacion getMarcacionTerminado(Long id) {
      String sql = "from Marcacion where id = :id";
      return (Marcacion)this.manager.createQuery(sql, Marcacion.class).setParameter("id", id).getSingleResult();
   }
}
