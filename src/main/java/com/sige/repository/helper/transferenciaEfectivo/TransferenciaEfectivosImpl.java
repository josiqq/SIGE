package com.sige.repository.helper.transferenciaEfectivo;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.TransferenciaEfectivo;
import com.sige.repository.filter.TransferenciaEfectivoFilter;

public class TransferenciaEfectivosImpl implements TransferenciaEfectivosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<TransferenciaEfectivo> buscarTransferencia(TransferenciaEfectivoFilter transferenciaEfectivoFilter) {
      return this.manager
         .createQuery(
            "from TransferenciaEfectivo where (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null) and (cuentaOrigen = :cuentaOrigen or :cuentaOrigen is null)  and (cuentaDestino = :cuentaDestino or :cuentaDestino is null)",
            TransferenciaEfectivo.class
         )
         .setParameter("fechaDesde", transferenciaEfectivoFilter.getFechaDesde())
         .setParameter("fechaHasta", transferenciaEfectivoFilter.getFechaHasta())
         .setParameter("cuentaOrigen", transferenciaEfectivoFilter.getCuentaOrigen())
         .setParameter("cuentaDestino", transferenciaEfectivoFilter.getCuentaDestino())
         .getResultList();
   }

   @Override
   public BigDecimal totalImporte(TransferenciaEfectivoFilter transferenciaEfectivoFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from TransferenciaEfectivo where (fecha between(:fechaDesde)and(:fechaHasta)  or :fechaDesde is null or :fechaHasta is null) and (cuentaOrigen = :cuentaOrigen or :cuentaOrigen is null)  and (cuentaDestino = :cuentaDestino or :cuentaDestino is null)",
            BigDecimal.class
         )
         .setParameter("fechaDesde", transferenciaEfectivoFilter.getFechaDesde())
         .setParameter("fechaHasta", transferenciaEfectivoFilter.getFechaHasta())
         .setParameter("cuentaOrigen", transferenciaEfectivoFilter.getCuentaOrigen())
         .setParameter("cuentaDestino", transferenciaEfectivoFilter.getCuentaDestino())
         .getSingleResult();
   }
}
