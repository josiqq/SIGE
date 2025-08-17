package com.sige.repository.helper.itemCobro;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Cobro;
import com.sige.model.ItemCobro;
import com.sige.model.Venta;

public class ItemCobrosImpl implements ItemCobrosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemCobro> buscarPorVenta(Venta venta) {
      return this.manager.createQuery("from ItemCobro where venta = :venta", ItemCobro.class).setParameter("venta", venta).getResultList();
   }

   @Override
   public List<ItemCobro> getItemCobroByCobro(Cobro cobro) {
      String sql = "from ItemCobro where cobro = :cobro";
      return this.manager.createQuery(sql, ItemCobro.class).setParameter("cobro", cobro).getResultList();
   }

   @Override
   public List<ItemCobro> getByIdVenta(Long id) {
      return this.manager.createQuery("from ItemCobro where venta.id = :id", ItemCobro.class).setParameter("id", id).getResultList();
   }
}
