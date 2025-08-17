package com.sige.repository.helper.Compra;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Compra;
import com.sige.model.ItemCompra;
import com.sige.repository.filter.CompraFilter;

public class ComprasImpl implements ComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Compra> buscarCompra(CompraFilter compraFilter) {
      return this.manager
         .createQuery(
            "from Compra where (id =:id or :id is null) and (fecha between(:fechaIni)and(:fechaFin) or (:fechaIni is null or :fechaFin is null))", Compra.class
         )
         .setParameter("id", compraFilter.getId())
         .setParameter("fechaIni", compraFilter.getFechaIni())
         .setParameter("fechaFin", compraFilter.getFechaFin())
         .setMaxResults(compraFilter.getLimite())
         .getResultList();
   }

   @Override
   public List<ItemCompra> getItemCompraByCompra(Compra compra) {
      String sql = "from ItemCompra where compra = :compra";
      return this.manager.createQuery(sql, ItemCompra.class).setParameter("compra", compra).getResultList();
   }
}
