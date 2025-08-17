package com.sige.repository.helper.ajusteStock;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.AjusteStock;
import com.sige.repository.filter.AjusteStockFilter;

public class AjusteStocksImpl implements AjusteStocksQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<AjusteStock> buscarAjuste(AjusteStockFilter ajusteStockFilter) {
      String sql = "from AjusteStock where (id = :id or :id is null) and (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null) and  (deposito = :deposito or :deposito is null)  order by fecha desc";
      return this.manager
         .createQuery(sql, AjusteStock.class)
         .setParameter("id", ajusteStockFilter.getId())
         .setParameter("fechaDesde", ajusteStockFilter.getFechaDesde())
         .setParameter("fechaHasta", ajusteStockFilter.getFechaHasta())
         .setParameter("deposito", ajusteStockFilter.getDeposito())
         .getResultList();
   }

   @Override
   public BigDecimal getCantidadStock(Long producto, Long deposito) {
      Object result = this.manager
         .createNativeQuery("SELECT f_cantidad_stock(:producto, :deposito) as cantidad")
         .setParameter("producto", producto)
         .setParameter("deposito", deposito)
         .getSingleResult();
      return (BigDecimal)result;
   }
}
