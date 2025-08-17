package com.sige.repository.helper.extractoProducto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ExtractoProducto;
import com.sige.repository.filter.ExtractoProductoFilter;

public class ExtractoProductosImpl implements ExtractoProductosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ExtractoProducto> buscarExtracto(ExtractoProductoFilter extractoProductoFilter) {
      return this.manager
         .createQuery(
            "from ExtractoProducto where (producto = :producto) and (fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null))  and(deposito = :deposito or :deposito is null)",
            ExtractoProducto.class
         )
         .setParameter("producto", extractoProductoFilter.getProducto())
         .setParameter("fechaDesde", extractoProductoFilter.getFechaDesde())
         .setParameter("fechaHasta", extractoProductoFilter.getFechaHasta())
         .setParameter("deposito", extractoProductoFilter.getDeposito())
         .getResultList();
   }
}
