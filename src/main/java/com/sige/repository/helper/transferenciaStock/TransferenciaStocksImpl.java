package com.sige.repository.helper.transferenciaStock;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ItemTransferenciaStock;
import com.sige.model.TransferenciaStock;
import com.sige.repository.filter.TransferenciaStockFilter;

public class TransferenciaStocksImpl implements TransferenciaStocksQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<TransferenciaStock> getTransferencias(TransferenciaStockFilter filter) {
      String sql = "from TransferenciaStock where (id = :id or :id is null) and fecha between(:fechaDesde) and (:fechaHasta)";
      return this.manager
         .createQuery(sql, TransferenciaStock.class)
         .setParameter("id", filter.getId())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .getResultList();
   }

   @Override
   public List<ItemTransferenciaStock> getAllItemsByTransferencia(TransferenciaStock transferencia) {
      String sql = "from ItemTransferenciaStock where transferenciaStock = :transferenciaStock";
      return this.manager.createQuery(sql, ItemTransferenciaStock.class).setParameter("transferenciaStock", transferencia).getResultList();
   }
}
