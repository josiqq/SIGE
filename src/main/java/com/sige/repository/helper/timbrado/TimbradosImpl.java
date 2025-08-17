package com.sige.repository.helper.timbrado;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Timbrado;

public class TimbradosImpl implements TimbradosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Timbrado> getTimbrado(Timbrado timbrado) {
      String sql = "from Timbrado where (id = :id or :id is null) and (numero = :numero or :numero is null) and  (fechaInicio>= :fechaInicio or :fechaInicio is null) and (fechaFin <= :fechaFin or :fechaFin is null)";
      return this.manager
         .createQuery(sql, Timbrado.class)
         .setParameter("id", timbrado.getId())
         .setParameter("numero", timbrado.getNumero())
         .setParameter("fechaInicio", timbrado.getFechaInicio())
         .setParameter("fechaFin", timbrado.getFechaFin())
         .getResultList();
   }

   @Override
   public Optional<Timbrado> optionalTimbrado(Timbrado timbrado) {
      String sql = "from Timbrado where expedicion = :expedicion and numero = :numero";
      List<Timbrado> result = this.manager
         .createQuery(sql, Timbrado.class)
         .setParameter("expedicion", timbrado.getExpedicion())
         .setParameter("numero", timbrado.getNumero())
         .getResultList();
      return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
   }

   @Override
   public List<Timbrado> getTimbradosActivos() {
      String sql = "from Timbrado where activo = true";
      return this.manager.createQuery(sql, Timbrado.class).getResultList();
   }
}
