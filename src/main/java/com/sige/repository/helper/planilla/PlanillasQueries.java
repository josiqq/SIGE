package com.sige.repository.helper.planilla;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sige.dto.PlanillaImporteDTO;
import com.sige.model.Cuenta;
import com.sige.model.ItemPlanillaRendicion;
import com.sige.model.Planilla;
import com.sige.repository.filter.PlanillaFilter;

public interface PlanillasQueries {
   Optional<Planilla> getByCuenta(Cuenta cuenta);

   List<Planilla> getPlanillas(PlanillaFilter planillaFilter);

   List<Planilla> getPlanillasByFecha(LocalDate fecha);

   List<Planilla> getPlanillasAbiertas();

   List<PlanillaImporteDTO> getPlanillasImportes(Long planilla);

   void eliminarItemPlanillaRendicion(Long idPlanilla);

   List<ItemPlanillaRendicion> getItemPlanillaRendiciones(Long idPlanilla);
}
