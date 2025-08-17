package com.sige.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.PersistenceException;
import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Cajero;
import com.sige.repository.Cajeros;
import com.sige.repository.filter.CajeroFilter;
import com.sige.service.exeption.NegocioException;

@Service
public class CajeroService {
   @Autowired
   private Cajeros cajeros;

   public List<Cajero> buscarCajerosActivos() {
      return this.cajeros.buscarCajerosActivos();
   }

   public void guardar(Cajero cajero) {
      if (StringUtils.isEmpty(cajero.getDocumento())) {
         cajero.setDocumento(UUID.randomUUID().toString());
      }

      Optional<Cajero> cajeroOp = this.cajeros.findByDocumento(cajero.getDocumento());
      if (cajeroOp.isPresent() && !cajeroOp.get().equals(cajero)) {
         throw new NegocioException("Este cajero: " + cajeroOp.get().getNombre() + " ya tiene ese mismo documento: " + cajero.getDocumento());
      } else {
         this.cajeros.save(cajero);
      }
   }

   public List<Cajero> buscarPorNombreDocumento(CajeroFilter cajeroFilter) {
      return this.cajeros.buscarPorNombreDocumento(cajeroFilter);
   }

   public Cajero buscarPorId(Long id) {
      return (Cajero)this.cajeros.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.cajeros.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
