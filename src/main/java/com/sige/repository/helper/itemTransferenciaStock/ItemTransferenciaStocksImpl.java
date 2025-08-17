package com.sige.repository.helper.itemTransferenciaStock;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class ItemTransferenciaStocksImpl implements ItemTransferenciaStocksQueries {
   @PersistenceContext
   private EntityManager manager;

   @Transactional
   @Override
   public void elimianrItem(Long idTransferencia) {
      String sql = "delete from item_transferencia_stock where id_transferencia_stock = :id";
      this.manager.createNativeQuery(sql).setParameter("id", idTransferencia).executeUpdate();
   }
}
