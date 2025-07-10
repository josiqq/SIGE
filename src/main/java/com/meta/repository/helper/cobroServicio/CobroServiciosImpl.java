package com.meta.repository.helper.cobroServicio;

import com.meta.modelo.CobroServicio;
import com.meta.repository.filter.CobroServicioFilter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CobroServiciosImpl implements CobroServiciosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<CobroServicio> buscarCobroServicio(CobroServicioFilter cobroServicioFilter) {
      return this.manager
         .createQuery(
            "from CobroServicio where (cliente = :cliente or :cliente is null) and(fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null)) and (vendedor = :vendedor or :vendedor is null)",
            CobroServicio.class
         )
         .setParameter("cliente", cobroServicioFilter.getCliente())
         .setParameter("fechaDesde", cobroServicioFilter.getFechaDesde())
         .setParameter("fechaHasta", cobroServicioFilter.getFechaHasta())
         .setParameter("vendedor", cobroServicioFilter.getVendedor())
         .getResultList();
   }

   @Override
   public BigDecimal totalImporte(CobroServicioFilter cobroServicioFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from CobroServicio  where (cliente = :cliente or :cliente is null) and (fecha between(:fechaDesde)and(:fechaHasta) or(:fechaDesde is null or :fechaHasta is null)) and (vendedor = :vendedor or :vendedor is null)",
            BigDecimal.class
         )
         .setParameter("cliente", cobroServicioFilter.getCliente())
         .setParameter("fechaDesde", cobroServicioFilter.getFechaDesde())
         .setParameter("fechaHasta", cobroServicioFilter.getFechaHasta())
         .setParameter("vendedor", cobroServicioFilter.getVendedor())
         .getSingleResult();
   }
}
