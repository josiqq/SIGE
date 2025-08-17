package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Grupo;
import com.sige.repository.Grupos;
import com.sige.service.exeption.NegocioException;

@Service
public class GrupoService {
   @Autowired
   private Grupos grupos;

   public List<Grupo> buscarTodos() {
      return this.grupos.findAll();
   }

   public Grupo buscarPorId(Long id) {
      return (Grupo)this.grupos.findById(id).orElse(null);
   }

   public void guardar(Grupo grupo) {
      Optional<Grupo> optionNombre = this.grupos.findByNombre(grupo.getNombre());
      if (optionNombre.isPresent() && optionNombre.get().getId() != grupo.getId()) {
         throw new NegocioException("Ya existe un grupo con el mismo nombre!");
      } else {
         this.grupos.save(grupo);
      }
   }

   public void eliminar(Long id) {
      try {
         this.grupos.deleteById(id);
         this.grupos.flush();
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   public List<Grupo> buscarPorNombre(Grupo grupo) {
      return this.grupos.buscarPorNombre(grupo);
   }
}
