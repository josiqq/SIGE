package com.sige.repository.helper.instructor;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Instructor;
import com.sige.repository.filter.InstructorFilter;

public class InstructoresImpl implements InstructoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Instructor> buscarNombreDocumento(InstructorFilter instructorFilter) {
      return this.manager
         .createQuery(
            "from Instructor where((nombre like :nombreDocumento or :nombreDocumento is null)or(documento = :documento or :documento is null)) ",
            Instructor.class
         )
         .setParameter("nombreDocumento", "%" + instructorFilter.getNombreDocumento() + "%")
         .setParameter("documento", instructorFilter.getNombreDocumento())
         .getResultList();
   }

   @Override
   public BigDecimal sadoInstructor(Long id) {
      return (BigDecimal)this.manager.createQuery("select saldo from Instructor where id = :id", BigDecimal.class).setParameter("id", id).getSingleResult();
   }
}
