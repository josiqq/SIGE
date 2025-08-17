package com.sige.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Membresia;
import com.sige.repository.Membresias;
import com.sige.repository.filter.MembresiaFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class MembresiaService {
   @Autowired
   private Membresias membresias;

   public void guardar(Membresia membresia) {
      this.membresias.save(membresia);
   }

   public void eliminar(Long id) {
      try {
         this.membresias.deleteById(id);
         this.membresias.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("No se puede eliminar, ya tuvo movimiento");
      }
   }

   public List<Membresia> buscar(MembresiaFilter membresiaFilter) {
      return this.membresias.buscarPorFecha(membresiaFilter);
   }

   public Membresia buscarPorId(Long id) {
      return (Membresia)this.membresias.findById(id).orElse(null);
   }

   public BigDecimal totalImporte(MembresiaFilter membresiaFilter) {
      return this.membresias.totalImporte(membresiaFilter);
   }

   public LocalDate ultimaFecha(Long id) {
      return this.membresias.ultimaFecha(id);
   }
}
