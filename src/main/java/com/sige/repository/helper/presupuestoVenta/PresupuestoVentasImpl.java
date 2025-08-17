package com.sige.repository.helper.presupuestoVenta;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.PresupuestoVenta;
import com.sige.model.Vendedor;
import com.sige.repository.filter.PresupuestoVentaFilter;

public class PresupuestoVentasImpl implements PresupuestoVentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<PresupuestoVenta> getPresupuestoVentas(PresupuestoVentaFilter filter) {
      String sql = "from PresupuestoVenta where (id=:id or :id is null)  and (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)  order by fecha desc";
      return this.manager
         .createQuery(sql, PresupuestoVenta.class)
         .setParameter("id", filter.getId())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setMaxResults(filter.getLimite())
         .getResultList();
   }

   @Override
   public List<PresupuestoVenta> BuscarPresupuestoVenta(LocalDate fechaDesde, LocalDate fechaHasta, Vendedor vendedor) {
      String sql = "from PresupuestoVenta where fecha between(:fechaDesde)and(:fechaHasta) and (vendedor = :vendedor or :vendedor is null)";
      return this.manager
         .createQuery(sql, PresupuestoVenta.class)
         .setParameter("fechaDesde", fechaDesde)
         .setParameter("fechaHasta", fechaHasta)
         .setParameter("vendedor", vendedor)
         .getResultList();
   }

   @Override
   public List<ItemPresupuestoVenta> getItemPresupuesto(PresupuestoVenta presupuestoVenta) {
      String sql = "from ItemPresupuestoVenta where presupuestoVenta =:presupuestoVenta";
      return this.manager.createQuery(sql, ItemPresupuestoVenta.class).setParameter("presupuestoVenta", presupuestoVenta).getResultList();
   }
}
