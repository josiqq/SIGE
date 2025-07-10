package com.meta.repository.helper.Compra;

import com.meta.modelo.Compra;
import com.meta.modelo.ItemCompra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemComprasImpl implements ItemComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemCompra> buscarDetalleCompra(Compra compra) {
      return this.manager.createQuery("from ItemCompra where compra= :compra", ItemCompra.class).setParameter("compra", compra).getResultList();
   }
}
