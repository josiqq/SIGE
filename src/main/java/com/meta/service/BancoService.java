package com.meta.service;

import com.meta.modelo.Banco;
import com.meta.repository.Bancos;
import com.meta.service.exeption.NegocioException;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
