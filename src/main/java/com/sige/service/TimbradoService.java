package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Timbrado;
import com.sige.repository.Timbrados;
import com.sige.service.exeption.NegocioException;

@Service
public class TimbradoService {
   @Autowired
   private Timbrados timbrados;

   public void guardar(Timbrado timbrado) {
      Optional<Timbrado> timOp = this.timbrados.optionalTimbrado(timbrado);
      if (timOp.isPresent() && !timOp.get().equals(timbrado)) {
         throw new NegocioException(
            "Ya existe esta expedición: "
               + timbrado.getExpedicion()
               + " con el mismo número de timbrado: "
               + timbrado.getNumero()
               + " este es su id:  "
               + timOp.get().getId()
         );
      } else {
         this.timbrados.save(timbrado);
      }
   }

   public void eliminar(Long id) {
      try {
         this.timbrados.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   public Timbrado getById(Long id) {
      return (Timbrado)this.timbrados.findById(id).orElse(null);
   }

   public List<Timbrado> getTimbrados(Timbrado timbrado) {
      return this.timbrados.getTimbrado(timbrado);
   }
}
