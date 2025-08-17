package com.sige.repository.helper.auditoria;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Auditoria;
import com.sige.repository.filter.AuditoriaFilter;

public class AuditoriasImpl implements AuditoriasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Auditoria> getAuditoriasByFilter(AuditoriaFilter filter) {
      String sql = "from Auditoria where (tabla = :tabla or :tabla is null ) and (usuario = :usuario or :usuario is null)\tand fecha between(:fechaDesde)and(:fechaHasta)  and (tipo = :tipo or :tipo is null)";
      String usuario = filter.getUsuario() != null ? filter.getUsuario().getNombre() : null;
      String tabla = filter.tabla != null ? filter.tabla.getDescripcion() : null;
      String tipo = filter.tipo != null ? filter.tipo.getDescripcion() : null;
      return this.manager
         .createQuery(sql, Auditoria.class)
         .setParameter("usuario", usuario)
         .setParameter("tabla", tabla)
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setParameter("tipo", tipo)
         .getResultList();
   }
}
