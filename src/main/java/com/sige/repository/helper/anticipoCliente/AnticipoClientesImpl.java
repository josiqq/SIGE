package com.sige.repository.helper.anticipoCliente;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.AnticipoCliente;
import com.sige.repository.filter.AnticipoClienteFilter;

public class AnticipoClientesImpl implements AnticipoClientesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<AnticipoCliente> buscarAnticipo(AnticipoClienteFilter anticipoClienteFilter) {
      return this.manager
         .createQuery(
            "from AnticipoCliente where (cliente = :cliente or :cliente is null)  and (cuenta = :cuenta or :cuenta is null)  and (fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null) )",
            AnticipoCliente.class
         )
         .setParameter("cliente", anticipoClienteFilter.getCliente())
         .setParameter("cuenta", anticipoClienteFilter.getCuenta())
         .setParameter("fechaDesde", anticipoClienteFilter.getFechaDesde())
         .setParameter("fechaHasta", anticipoClienteFilter.getFechaHasta())
         .getResultList();
   }

   @Override
   public BigDecimal totalImporte(AnticipoClienteFilter anticipoClienteFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from AnticipoCliente where (cliente = :cliente or :cliente is null)  and (cuenta = :cuenta or :cuenta is null)  and (fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null) )",
            BigDecimal.class
         )
         .setParameter("cliente", anticipoClienteFilter.getCliente())
         .setParameter("cuenta", anticipoClienteFilter.getCuenta())
         .setParameter("fechaDesde", anticipoClienteFilter.getFechaDesde())
         .setParameter("fechaHasta", anticipoClienteFilter.getFechaHasta())
         .getSingleResult();
   }
}
