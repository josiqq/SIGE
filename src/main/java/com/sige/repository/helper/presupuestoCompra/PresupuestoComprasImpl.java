package com.sige.repository.helper.presupuestoCompra;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.PresupuestoCompra;
import com.sige.repository.filter.PresupuestoCompraFilter;

public class PresupuestoComprasImpl implements PresupuestoComprasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<PresupuestoCompra> getPresupuestoCompra(PresupuestoCompraFilter filter) {
      String sql = "from PresupuestoCompra where (id=:id or :id is null)  and (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)  order by fecha desc";
      return this.manager
         .createQuery(sql, PresupuestoCompra.class)
         .setParameter("id", filter.getId())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setMaxResults(filter.getLimite())
         .getResultList();
   }

   @Override
   public List<PresupuestoCompra> getPresupuestoByFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
      String sql = "from PresupuestoCompra where fecha between(:fechaDesde)and(:fechaHasta)";
      return this.manager
         .createQuery(sql, PresupuestoCompra.class)
         .setParameter("fechaDesde", fechaDesde)
         .setParameter("fechaHasta", fechaHasta)
         .getResultList();
   }

   @Override
   public List<ItemPresupuestoCompra> getItemPresupuesto(Long id) {
      return this.manager
         .createQuery("from ItemPresupuestoCompra where presupuestoCompra.id = :id ", ItemPresupuestoCompra.class)
         .setParameter("id", id)
         .getResultList();
   }
}
