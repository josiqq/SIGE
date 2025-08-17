package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Condicion;
import com.sige.repository.Condiciones;
import com.sige.service.exeption.NegocioException;

@Service
public class CondicionService {
   @Autowired
   private Condiciones condiciones;

   public void guardar(Condicion condicion) {
      Optional<Condicion> conicionOp = this.condiciones.findByNombre(condicion.getNombre());
      if (conicionOp.isPresent() && !conicionOp.get().equals(condicion)) {
         throw new NegocioException("Ya existe una condicion con el mismo nombre!");
      } else {
         this.condiciones.save(condicion);
      }
   }

   public Condicion getById(Long id) {
      return (Condicion)this.condiciones.findById(id).orElse(null);
   }

   public List<Condicion> getAllCondiciones() {
      return this.condiciones.findAll();
   }

   public void eliminar(Long id) {
      try {
         this.condiciones.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("imposible eliminar, ya tuvo movimiento!");
      }
   }
}
