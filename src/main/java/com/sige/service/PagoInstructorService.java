package com.sige.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.PagoInstructor;
import com.sige.repository.PagoInstructores;
import com.sige.repository.filter.PagoInstructorFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class PagoInstructorService {
   @Autowired
   private PagoInstructores pagoInstructores;

   public void guardar(PagoInstructor pagoInstructor) {
      this.pagoInstructores.save(pagoInstructor);
   }

   public void eliminar(Long id) {
      try {
         this.pagoInstructores.deleteById(id);
         this.pagoInstructores.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   public PagoInstructor buscarPorId(Long id) {
      return (PagoInstructor)this.pagoInstructores.findById(id).orElse(null);
   }

   public List<PagoInstructor> buscarPago(PagoInstructorFilter pagoInstructorFilter) {
      return this.pagoInstructores.buscarPago(pagoInstructorFilter);
   }

   public BigDecimal totalImporte(PagoInstructorFilter pagoInstructorFilter) {
      return this.pagoInstructores.totalImporte(pagoInstructorFilter);
   }
}
