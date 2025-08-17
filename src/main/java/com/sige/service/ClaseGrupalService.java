package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.ClaseGrupal;
import com.sige.repository.ClaseGrupales;
import com.sige.service.exeption.NegocioException;

@Service
public class ClaseGrupalService {
   @Autowired
   private ClaseGrupales claseGrupales;

   public void guardar(ClaseGrupal claseGrupal) {
      Optional<ClaseGrupal> claseGrupalOP = this.claseGrupales.findByNombre(claseGrupal.getNombre());
      if (claseGrupalOP.isPresent() && !claseGrupalOP.get().equals(claseGrupal)) {
         throw new NegocioException("Ya existe un grupo con este mismo nombre su id es: " + claseGrupalOP.get().getId());
      } else if (claseGrupal.getItems().size() == 0) {
         throw new NegocioException("No se puede grabar clase grupal sin integrantes!");
      } else {
         this.claseGrupales.save(claseGrupal);
      }
   }

   public List<ClaseGrupal> listaClases(ClaseGrupal claseGrupal) {
      return this.claseGrupales.listaDeClases(claseGrupal);
   }

   public List<ClaseGrupal> buscarTodas() {
      return this.claseGrupales.findAll();
   }

   public ClaseGrupal buscarPorId(Long id) {
      return (ClaseGrupal)this.claseGrupales.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.claseGrupales.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
