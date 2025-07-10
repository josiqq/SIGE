package com.meta.repository.helper.pagoVendedor;

import com.meta.modelo.PagoVendedor;
import com.meta.repository.filter.PagoVendedorFilter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PagoVendedoresImpl implements PagoVendedoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<PagoVendedor> buscarPagoVendedores(PagoVendedorFilter pagoVendedorFilter) {
      return this.manager
         .createQuery(
            "from PagoVendedor where fecha between(:fechaDesde)and(:fechaHasta)and (vendedor = :vendedor or :vendedor is null) and (cuenta = :cuenta or :cuenta is null)",
            PagoVendedor.class
         )
         .setParameter("fechaDesde", pagoVendedorFilter.getFechaDesde())
         .setParameter("fechaHasta", pagoVendedorFilter.getFechaHasta())
         .setParameter("vendedor", pagoVendedorFilter.getVendedor())
         .setParameter("cuenta", pagoVendedorFilter.getCuenta())
         .getResultList();
   }

   @Override
   public BigDecimal totalPagoVendedor(PagoVendedorFilter pagoVendedorFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from PagoVendedor where fecha between(:fechaDesde)and(:fechaHasta)  and (vendedor = :vendedor or :vendedor is null) and (cuenta = :cuenta or :cuenta is null)",
            BigDecimal.class
         )
         .setParameter("fechaDesde", pagoVendedorFilter.getFechaDesde())
         .setParameter("fechaHasta", pagoVendedorFilter.getFechaHasta())
         .setParameter("vendedor", pagoVendedorFilter.getVendedor())
         .setParameter("cuenta", pagoVendedorFilter.getCuenta())
         .getSingleResult();
   }
}
