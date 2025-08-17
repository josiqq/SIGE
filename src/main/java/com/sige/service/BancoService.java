package com.sige.service;

import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Banco;
import com.sige.repository.Bancos;
import com.sige.service.exeption.NegocioException;

@Service
public class BancoService {
   @Autowired
   private Bancos bancos;

   public void guardar(Banco banco) {
      Optional<Banco> bancoOptional = this.bancos.findByNombre(banco.getNombre());
      if (bancoOptional.isPresent() && !bancoOptional.get().equals(banco)) {
         throw new NegocioException("Ya existe un banco con el mismo nombre! ");
      } else {
         this.bancos.save(banco);
      }
   }

   public void eliminar(Long id) {
      try {
         this.bancos.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
