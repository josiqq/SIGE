package com.meta.repository.helper.ajusteLote;

import com.meta.modelo.AjusteLote;
import com.meta.repository.filter.AjusteLoteFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AjusteLotesImpl implements AjusteLotesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<AjusteLote> getAjusteLotes(AjusteLoteFilter filter) {
      String sql = "select distinct al from AjusteLote al join al.items ial where (al.id = :id or :id is null) and  (ial.producto = :producto or :producto is null) and (al.deposito = :deposito or :deposito is null) and (al.fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)";
      return this.manager
         .createQuery(sql, AjusteLote.class)
         .setParameter("id", filter.getId())
         .setParameter("producto", filter.getProducto())
         .setParameter("deposito", filter.getDeposito())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .getResultList();
   }
}
