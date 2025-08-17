package com.sige.repository.helper.itemAjusteLote;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.model.ItemAjusteLote;

public class ItemAjusteLotesImpl implements ItemAjusteLotesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Transactional
   @Override
   public void eliminarByAjusteLote(Long id) {
      String sql = "delete from item_ajuste_lote where id_ajuste_lote = :id";
      this.manager.createNativeQuery(sql).setParameter("id", id).executeUpdate();
   }

   @Override
   public List<ItemAjusteLote> getItemsLotes(Long idAjusteLote) {
      String sql = "from ItemAjusteLote where ajusteLote.id =:id";
      return this.manager.createQuery(sql, ItemAjusteLote.class).setParameter("id", idAjusteLote).getResultList();
   }
}
