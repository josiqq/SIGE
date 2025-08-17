package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Deposito;
import com.sige.repository.Depositos;
import com.sige.service.exeption.NegocioException;

@Service
public class DepositoService {
   @Autowired
   private Depositos depositos;

   public void guardar(Deposito deposito) {
      Optional<Deposito> optionalNombre = this.depositos.findByNombre(deposito.getNombre());
      if (optionalNombre.isPresent() && !optionalNombre.get().equals(deposito)) {
         throw new NegocioException("Este deposito ya tiene el mismo nombre: " + optionalNombre.get().getNombre());
      } else {
         this.depositos.save(deposito);
      }
   }

   public Deposito buscarPorId(Long id) {
      return (Deposito)this.depositos.findById(id).orElse(null);
   }

   public List<Deposito> buscarDeposito(Deposito deposito) {
      return this.depositos.buscarDeposito(deposito);
   }

   public void eliminar(Long id) {
      try {
         this.depositos.deleteById(id);
         this.depositos.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
