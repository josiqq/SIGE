package com.sige.repository.helper.movimientoCaja;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.MovimientoCaja;
import com.sige.repository.filter.MovimientoCajaFilter;

public class MovimientoCajasImpl implements MovimientoCajasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<MovimientoCaja> buscarMovimiento(MovimientoCajaFilter movimientoCajaFilter) {
      return this.manager
         .createQuery("from MovimientoCaja where fecha between(:fechaDesde)and(:fechaHasta)and(cuenta_id=:cuenta )", MovimientoCaja.class)
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("fechaHasta", movimientoCajaFilter.getFechaHasta())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getResultList();
   }

   @Override
   public BigDecimal totalCredito(MovimientoCajaFilter movimientoCajaFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select coalesce(sum(credito),0) from MovimientoCaja where fecha between(:fechaDesde)and(:fechaHasta)and(cuenta_id=:cuenta )", BigDecimal.class
         )
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("fechaHasta", movimientoCajaFilter.getFechaHasta())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getSingleResult();
   }

   @Override
   public BigDecimal totalDebito(MovimientoCajaFilter movimientoCajaFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select coalesce(sum(debito),0) from MovimientoCaja where fecha between(:fechaDesde)and(:fechaHasta)and(cuenta_id=:cuenta )", BigDecimal.class
         )
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("fechaHasta", movimientoCajaFilter.getFechaHasta())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getSingleResult();
   }

   @Override
   public BigDecimal saldo(MovimientoCajaFilter movimientoCajaFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select coalesce((sum(credito)-sum(debito)),0) from MovimientoCaja where(cuenta_id=:cuenta ) and fecha between(:fechaDesde)and(:fechaHasta)",
            BigDecimal.class
         )
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("fechaHasta", movimientoCajaFilter.getFechaHasta())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getSingleResult();
   }

   @Override
   public BigDecimal creditoAnterior(MovimientoCajaFilter movimientoCajaFilter) {
      String sql = "select coalesce(sum(credito),0) from MovimientoCaja where fecha<:fechaDesde and cuenta = :cuenta";
      return (BigDecimal)this.manager
         .createQuery(sql, BigDecimal.class)
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getSingleResult();
   }

   @Override
   public BigDecimal debitoAnterior(MovimientoCajaFilter movimientoCajaFilter) {
      String sql = "select coalesce(sum(debito),0) from MovimientoCaja where fecha<:fechaDesde and cuenta = :cuenta";
      return (BigDecimal)this.manager
         .createQuery(sql, BigDecimal.class)
         .setParameter("fechaDesde", movimientoCajaFilter.getFechaDesde())
         .setParameter("cuenta", movimientoCajaFilter.getCuenta())
         .getSingleResult();
   }
}
