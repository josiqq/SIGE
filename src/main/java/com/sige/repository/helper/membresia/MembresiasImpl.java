package com.sige.repository.helper.membresia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Membresia;
import com.sige.repository.filter.MembresiaFilter;

public class MembresiasImpl implements MembresiasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public BigDecimal totalImporte(MembresiaFilter membresiaFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from Membresia where fecha between(:fechaDesde)and(:fechaHasta) and(cliente=:cliente or :cliente is null)", BigDecimal.class
         )
         .setParameter("fechaDesde", membresiaFilter.getFechaDesde())
         .setParameter("fechaHasta", membresiaFilter.getFechaHasta())
         .setParameter("cliente", membresiaFilter.getCliente())
         .getSingleResult();
   }

   @Override
   public List<Membresia> buscarPorFecha(MembresiaFilter membresiaFilter) {
      return this.manager
         .createQuery("from Membresia where fecha between(:fechaDesde)and(:fechaHasta)and(cliente=:cliente or :cliente is null)", Membresia.class)
         .setParameter("fechaDesde", membresiaFilter.getFechaDesde())
         .setParameter("fechaHasta", membresiaFilter.getFechaHasta())
         .setParameter("cliente", membresiaFilter.getCliente())
         .getResultList();
   }

   @Override
   public BigDecimal membresiaDelDia() {
      return (BigDecimal)this.manager.createQuery("select sum(importe) from Membresia where fecha = curdate()", BigDecimal.class).getSingleResult();
   }

   @Override
   public LocalDate ultimaFecha(Long id) {
      return (LocalDate)this.manager
         .createQuery("select max(fechaEstimado) from Membresia where cliente.id = :id", LocalDate.class)
         .setParameter("id", id)
         .getSingleResult();
   }
}
