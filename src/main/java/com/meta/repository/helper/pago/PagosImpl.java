package com.meta.repository.helper.pago;

import com.meta.modelo.Pago;
import com.meta.repository.filter.PagoFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PagosImpl implements PagosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Pago> getPagos(PagoFilter pagoFilter) {
      String sql = "from Pago where (id= :id or :id is null) and (fecha between(:fechaDesde)and(:fechaHasta) or(:fechaDesde is null or :fechaHasta is null))";
      return this.manager
         .createQuery(sql, Pago.class)
         .setParameter("id", pagoFilter.getId())
         .setParameter("fechaDesde", pagoFilter.getFechaDesde())
         .setParameter("fechaHasta", pagoFilter.getFechaHasta())
         .setMaxResults(pagoFilter.getLimite())
         .getResultList();
   }
}
