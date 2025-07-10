package com.meta.repository.helper.itemPago;

import com.meta.modelo.Compra;
import com.meta.modelo.ItemPago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemPagosImpl implements ItemPagosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemPago> getItemPagos(Compra compra) {
      return this.manager.createQuery("from ItemPago where compra =:compra", ItemPago.class).setParameter("compra", compra).getResultList();
   }
}
