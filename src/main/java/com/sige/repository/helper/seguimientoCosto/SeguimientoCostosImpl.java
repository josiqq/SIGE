package com.sige.repository.helper.seguimientoCosto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.SeguimientoCosto;
import com.sige.repository.filter.SeguimientoCostoFilter;

public class SeguimientoCostosImpl implements SeguimientoCostosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<SeguimientoCosto> buscarSeguimieto(SeguimientoCostoFilter seguimientoCostoFilter) {
      return this.manager
         .createQuery(
            "from SeguimientoCosto where producto = :producto and(fecha between(:fechaDesde)and(:fechaHasta) or  (:fechaDesde is null or :fechaHasta is null))",
            SeguimientoCosto.class
         )
         .setParameter("producto", seguimientoCostoFilter.getProducto())
         .setParameter("fechaDesde", seguimientoCostoFilter.getFechaDesde())
         .setParameter("fechaHasta", seguimientoCostoFilter.getFechaHasta())
         .getResultList();
   }
}
