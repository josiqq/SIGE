package com.sige.service;

import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.PresupuestoCompra;
import com.sige.repository.PresupuestoCompras;
import com.sige.service.exeption.NegocioException;

@Service
public class PresupuestoCompraService {
   @Autowired
   private PresupuestoCompras presupuestos;

   public void guardar(PresupuestoCompra presupuestoCompra) {
      this.validaciones(presupuestoCompra);
      this.presupuestos.save(presupuestoCompra);
   }

   public void eliminar(Long id) {
      try {
         this.presupuestos.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento");
      }
   }

   private void validaciones(PresupuestoCompra presupuestoCompra) {
      if (presupuestoCompra.getItems().size() == 0) {
         throw new NegocioException("No puede guardar Presupuesto sin detalles ");
      }
   }
}
