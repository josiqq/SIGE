package com.sige.repository.helper.Compra;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Compra;
import com.sige.model.ItemCompra;

public class ItemComprasImpl implements ItemComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemCompra> buscarDetalleCompra(Compra compra) {
      return this.manager.createQuery("from ItemCompra where compra= :compra", ItemCompra.class).setParameter("compra", compra).getResultList();
   }
}
