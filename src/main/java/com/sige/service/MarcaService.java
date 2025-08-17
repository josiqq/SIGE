package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Marca;
import com.sige.repository.Marcas;
import com.sige.service.exeption.NegocioException;

@Service
public class MarcaService {
   @Autowired
   private Marcas marcas;

   public void guardar(Marca marca) {
      Optional<Marca> marcaOptional = this.marcas.findByNombre(marca.getNombre());
      if (marcaOptional.isPresent() && !marcaOptional.get().equals(marca)) {
         throw new NegocioException("Ya existe una marca con el mismo nombre!");
      } else {
         this.marcas.save(marca);
      }
   }

   public List<Marca> buscarMarca(Marca marca) {
      return this.marcas.buscarMarca(marca);
   }

   public Marca buscarPorId(Long id) {
      return (Marca)this.marcas.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.marcas.deleteById(id);
         this.marcas.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
