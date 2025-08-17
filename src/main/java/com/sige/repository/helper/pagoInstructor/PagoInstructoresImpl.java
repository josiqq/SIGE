package com.sige.repository.helper.pagoInstructor;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.PagoInstructor;
import com.sige.repository.filter.PagoInstructorFilter;

public class PagoInstructoresImpl implements PagoInstructoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<PagoInstructor> buscarPago(PagoInstructorFilter pagoInstructorFilter) {
      return this.manager
         .createQuery(
            "from PagoInstructor where fecha between(:fechaIni)and(:fechaFin) and (instructor = :instructor or :instructor is null)", PagoInstructor.class
         )
         .setParameter("fechaIni", pagoInstructorFilter.getFechaIni())
         .setParameter("fechaFin", pagoInstructorFilter.getFechaFin())
         .setParameter("instructor", pagoInstructorFilter.getInstructor())
         .getResultList();
   }

   @Override
   public BigDecimal totalImporte(PagoInstructorFilter pagoInstructorFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(importe) from PagoInstructor where fecha between(:fechaIni)and(:fechaFin)and (instructor=:instructor or :instructor is null)",
            BigDecimal.class
         )
         .setParameter("fechaIni", pagoInstructorFilter.getFechaIni())
         .setParameter("fechaFin", pagoInstructorFilter.getFechaFin())
         .setParameter("instructor", pagoInstructorFilter.getInstructor())
         .getSingleResult();
   }
}
