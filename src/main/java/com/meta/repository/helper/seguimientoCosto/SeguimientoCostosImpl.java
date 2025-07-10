package com.meta.repository.helper.seguimientoCosto;

import com.meta.modelo.SeguimientoCosto;
import com.meta.repository.filter.SeguimientoCostoFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
