package com.meta.service;

import com.meta.modelo.Parametro;
import com.meta.modelo.Planilla;
import com.meta.repository.Parametros;
import com.meta.repository.Planillas;
import com.meta.repository.filter.PlanillaFilter;
import com.meta.service.exeption.NegocioException;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanillaService {
   @Autowired
   private Planillas planillas;
   @Autowired
   private Parametros parametros;

   public void guardar(Planilla planilla) {
      Parametro parametro = this.parametros.getParametro();
      Optional<Planilla> PlanillaOp = this.planillas.getByCuenta(planilla.getCuenta());
      if (!parametro.isAbrePlanilla()) {
         throw new NegocioException("El parametro para abrir planilla está desactivado!");
      } else if (PlanillaOp.isPresent() && !PlanillaOp.get().equals(planilla)) {
         throw new NegocioException("Ya existe una planilla abierta con esta cuenta, este es el numero de planilla: " + PlanillaOp.get().getId());
      } else {
         this.planillas.save(planilla);
      }
   }

   public void guardarPlanillaCierre(Planilla planilla) {
      if (!planilla.isReAbrir()) {
         planilla.setCerrado(true);
         planilla.setDiferencia(planilla.getTotalRendido().subtract(planilla.getTotal()));
         this.planillas.save(planilla);
      } else {
         planilla.setCerrado(false);
         this.planillas.save(planilla);
         this.eliminarItemPlanillaImporte(planilla.getId());
      }
   }

   public void eliminarItemPlanillaImporte(Long id) {
      this.planillas.eliminarItemPlanillaRendicion(id);
   }

   public List<Planilla> getPlanillas(PlanillaFilter planillaFilter) {
      return this.planillas.getPlanillas(planillaFilter);
   }

   public Planilla getById(Long id) {
      return (Planilla)this.planillas.findById(id).orElse(null);
   }

   public void eliminar(Long id) {
      try {
         this.planillas.deleteById(id);
      } catch (PersistenceException var3) {
         throw new NegocioException("Imposible Eliminar, ya tuvo movimiento");
      }
   }
}
