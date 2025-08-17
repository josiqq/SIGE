package com.sige.repository.helper.gasto;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Gasto;
import com.sige.repository.filter.GastoFilter;

public class GastosImpl implements GastosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Gasto> BuscarGasto(GastoFilter gastoFilter) {
      return this.manager
         .createQuery(
            "from Gasto where fecha between (:fechaDesde)and(:fechaHasta) and(proveedor=:proveedor or :proveedor is null) and(moneda =:moneda or :moneda is null)",
            Gasto.class
         )
         .setParameter("fechaDesde", gastoFilter.getFechaDesde())
         .setParameter("fechaHasta", gastoFilter.getFechaHasta())
         .setParameter("proveedor", gastoFilter.getProveedor())
         .setParameter("moneda", gastoFilter.getMoneda())
         .getResultList();
   }

   @Override
   public BigDecimal totalImporte(GastoFilter gastoFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from Gasto where fecha between(:fechaDesde)and(:fechaHasta) and(proveedor=:proveedor or :proveedor is null) and (moneda = :moneda or :moneda is null)",
            BigDecimal.class
         )
         .setParameter("fechaDesde", gastoFilter.getFechaDesde())
         .setParameter("fechaHasta", gastoFilter.getFechaHasta())
         .setParameter("proveedor", gastoFilter.getProveedor())
         .setParameter("moneda", gastoFilter.getMoneda())
         .getSingleResult();
   }

   @Override
   public BigDecimal gastoDelDia() {
      return (BigDecimal)this.manager.createQuery("select sum(importe) from Gasto where fecha = curdate()", BigDecimal.class).getSingleResult();
   }
}
