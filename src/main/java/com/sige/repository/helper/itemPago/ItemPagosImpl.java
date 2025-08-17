package com.sige.repository.helper.itemPago;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Compra;
import com.sige.model.ItemPago;

public class ItemPagosImpl implements ItemPagosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemPago> getItemPagos(Compra compra) {
      return this.manager.createQuery("from ItemPago where compra =:compra", ItemPago.class).setParameter("compra", compra).getResultList();
   }
}
