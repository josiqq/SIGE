package com.meta.repository.helper.movInstructor;

import com.meta.modelo.MovInstructor;
import com.meta.repository.filter.MovInstructorFilter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MovInstructoresImpl implements MovInstructoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<MovInstructor> buscarMovInstructor(MovInstructorFilter movInstructorFilter) {
      return this.manager
         .createQuery(
            "from MovInstructor where fecha between(:fechaIni)and(:fechaFin) and (instructor = :instructor or :instructor is null) and observacion = 'Membresia'",
            MovInstructor.class
         )
         .setParameter("fechaIni", movInstructorFilter.getFechaIni())
         .setParameter("fechaFin", movInstructorFilter.getFechaFin())
         .setParameter("instructor", movInstructorFilter.getInstructor())
         .getResultList();
   }

   @Override
   public List<MovInstructor> buscarPorInstructor(MovInstructorFilter movInstructorFilter) {
      return this.manager
         .createQuery(
            "from MovInstructor where fecha between(:fechaIni)and(:fechaFin) and (instructor = :instructor) and observacion = 'Membresia'", MovInstructor.class
         )
         .setParameter("fechaIni", movInstructorFilter.getFechaIni())
         .setParameter("fechaFin", movInstructorFilter.getFechaFin())
         .setParameter("instructor", movInstructorFilter.getInstructor())
         .getResultList();
   }

   @Override
   public BigDecimal totalMovInstructor(MovInstructorFilter movInstructorFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(credito)from MovInstructor where fecha between(:fechaIni)and(:fechaFin) and (instructor = :instructor or :instructor is null) and observacion = 'Membresia'",
            BigDecimal.class
         )
         .setParameter("fechaIni", movInstructorFilter.getFechaIni())
         .setParameter("fechaFin", movInstructorFilter.getFechaFin())
         .setParameter("instructor", movInstructorFilter.getInstructor())
         .getSingleResult();
   }

   @Override
   public BigDecimal totalPorInstructor(MovInstructorFilter movInstructorFilter) {
      return (BigDecimal)this.manager
         .createQuery(
            "select sum(credito)from MovInstructor where fecha between(:fechaIni)and(:fechaFin) and (instructor = :instructor) and observacion = 'Membresia'",
            BigDecimal.class
         )
         .setParameter("fechaIni", movInstructorFilter.getFechaIni())
         .setParameter("fechaFin", movInstructorFilter.getFechaFin())
         .setParameter("instructor", movInstructorFilter.getInstructor())
         .getSingleResult();
   }
}
