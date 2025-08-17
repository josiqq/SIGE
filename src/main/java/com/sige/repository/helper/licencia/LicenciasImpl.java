package com.sige.repository.helper.licencia;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Licencia;

public class LicenciasImpl implements LicenciasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public LocalDate buscarUltimaFecha() {
      String sql = "select max(fecha) from Licencia ";
      return (LocalDate)this.manager.createQuery(sql, LocalDate.class).getSingleResult();
   }

   @Override
   public Licencia getLicenciaByFecha(LocalDate fecha) {
      String sql = "from Licencia where fecha = :fecha";
      return (Licencia)this.manager.createQuery(sql, Licencia.class).setParameter("fecha", fecha).getSingleResult();
   }

   @Override
   public Long buscarUltimoId() {
      String sql = "select max(id) from Licencia";
      return (Long)this.manager.createQuery(sql, Long.class).getSingleResult();
   }
}
