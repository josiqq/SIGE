package com.sige.repository.helper.licenciaFecha;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class LicenciaFechasImpl implements LicenciaFechasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public LocalDate getMaxFecha() {
      String sql = "select max(fecha) from LicenciaFecha";
      return (LocalDate)this.manager.createQuery(sql, LocalDate.class).getSingleResult();
   }
}
