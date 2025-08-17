package com.sige.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Moneda;
import com.sige.repository.Monedas;
import com.sige.service.exeption.NegocioException;

@Service
public class MonedaService {
   @Autowired
   private Monedas monedas;

   public void guardar(Moneda moneda) {
      Optional<Moneda> monedaOpt = this.monedas.findByNombre(moneda.getNombre());
      if (monedaOpt.isPresent() && !monedaOpt.get().equals(moneda)) {
         throw new NegocioException("Ya existe una moneda con ese nombre!");
      } else {
         this.monedas.save(moneda);
      }
   }

   public Moneda getById(Long id) {
      return (Moneda)this.monedas.findById(id).orElse(null);
   }

   public List<Moneda> getAllMonedas() {
      return this.monedas.findAll();
   }

   public void eliminar(Long id) {
      try {
         this.monedas.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
