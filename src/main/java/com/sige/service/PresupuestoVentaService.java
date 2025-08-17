package com.sige.service;

import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.CondicionVenta;
import com.sige.model.PresupuestoVenta;
import com.sige.repository.PresupuestoVentas;
import com.sige.service.exeption.NegocioException;

@Service
public class PresupuestoVentaService {
   @Autowired
   private PresupuestoVentas presupuestoVentas;

   public void guardar(PresupuestoVenta presupuestoVenta) {
      this.validarPresupuesto(presupuestoVenta);
      this.presupuestoVentas.save(presupuestoVenta);
   }

   private void validarPresupuesto(PresupuestoVenta presupuestoVenta) {
      presupuestoVenta.setCondicionVenta(CondicionVenta.CONTADO);
      if (presupuestoVenta.getItems().size() == 0) {
         throw new NegocioException("No puede guardar sin detalles!");
      }
   }

   public void eliminar(Long id) {
      try {
         this.presupuestoVentas.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible eliminar, ya tuvo movimiento!");
      }
   }
}
