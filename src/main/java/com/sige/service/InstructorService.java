package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Instructor;
import com.sige.repository.Instructores;
import com.sige.repository.filter.InstructorFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class InstructorService {
   @Autowired
   private Instructores instructores;

   public void guardar(Instructor instructor) {
      Optional<Instructor> optionalInstructor = this.instructores.findByDocumento(instructor.getDocumento());
      if (optionalInstructor.isPresent() && optionalInstructor.get().getId() != instructor.getId()) {
         throw new NegocioException("Ya existe un Instructor con el mismo número de documento. Doc: " + instructor.getDocumento());
      } else {
         this.instructores.save(instructor);
      }
   }

   public void eliminar(Long id) {
      try {
         this.instructores.deleteById(id);
         this.instructores.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("No puede eliminar Instructor, ya tuvo movimiento");
      }
   }

   public Instructor buscarPorId(Long id) {
      return (Instructor)this.instructores.findById(id).orElse(null);
   }

   public List<Instructor> buscarNombreDocumento(InstructorFilter intructorFilter) {
      return this.instructores.buscarNombreDocumento(intructorFilter);
   }

   public List<Instructor> buscarTodos() {
      return this.instructores.findAll();
   }

   public BigDecimal saldoInstructor(Long id) {
      return this.instructores.sadoInstructor(id);
   }
}
