package com.sige.repository.helper.itemVentaLote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class ItemVentaLotesImpl implements ItemVentaLotesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Transactional
   @Override
   public void eliminarItemVentaLote(Long idVenta) {
      String sql = "delete from item_venta_lote  where id_venta =:id";
      this.manager.createNativeQuery(sql).setParameter("id", idVenta).executeUpdate();
   }
}
