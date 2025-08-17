package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sige.model.Consorcio;
import com.sige.repository.Consorcios;
import com.sige.service.exeption.NegocioException;

@Service
public class ConsorcioService {
   @Autowired
   private Consorcios consorcios;

   public void guardar(Consorcio consorcio) {
      Optional<Consorcio> consorcioOp = this.consorcios.findByNombre(consorcio.getNombre());
      if (consorcioOp.isPresent() && !consorcioOp.get().equals(consorcio)) {
         throw new NegocioException("Ya existe un consorcio con el mismo nombre!");
      } else {
         try {
            this.consorcios.save(consorcio);
         } catch (DataIntegrityViolationException var4) {
            throw new NegocioException("No se puede modificar, ya tiene pago de consorcio!");
         }
      }
   }

   public List<Consorcio> getConsorcios(Consorcio consorcio) {
      return this.consorcios.getConsorcios(consorcio);
   }

   public Consorcio gitById(Long id) {
      return (Consorcio)this.consorcios.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.consorcios.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
