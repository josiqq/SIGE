package com.sige.repository.helper.movimientoVendedor;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.sige.model.MovimientoVendedor;
import com.sige.model.Vendedor;

public class MovimientoVendedoresImpl implements MovimientoVendedoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Transactional
   @Override
   public void eliminarPorItemVenta(Long id) {
      this.manager.createNativeQuery("delete from movimiento_vendedor where id_item_venta = :id").setParameter("id", id).executeUpdate();
   }

   @Transactional
   @Override
   public void recalcularMovimiento(Long id_vendedor) {
      Query query = this.manager.createNativeQuery("CALL pr_movimiento_vendedor(:a_vendedor)").setParameter("a_vendedor", id_vendedor);
      query.executeUpdate();
   }

   @Override
   public List<MovimientoVendedor> getMovimiento(Vendedor vendedor, LocalDate fechaDesde, LocalDate fechaHasta) {
      String sql = "from MovimientoVendedor where (vendedor = :vendedor or :vendedor is null) and fecha between(:fechaDesde) and (:fechaHasta)";
      return this.manager
         .createQuery(sql, MovimientoVendedor.class)
         .setParameter("vendedor", vendedor)
         .setParameter("fechaDesde", fechaDesde)
         .setParameter("fechaHasta", fechaHasta)
         .getResultList();
   }
}
