package com.meta.repository.helper.planilla;

import com.meta.dto.PlanillaImporteDTO;
import com.meta.modelo.Cuenta;
import com.meta.modelo.ItemPlanillaRendicion;
import com.meta.modelo.Planilla;
import com.meta.repository.filter.PlanillaFilter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanillasQueries {
   Optional<Planilla> getByCuenta(Cuenta cuenta);

   List<Planilla> getPlanillas(PlanillaFilter planillaFilter);

   List<Planilla> getPlanillasByFecha(LocalDate fecha);

   List<Planilla> getPlanillasAbiertas();

   List<PlanillaImporteDTO> getPlanillasImportes(Long planilla);

   void eliminarItemPlanillaRendicion(Long idPlanilla);

   List<ItemPlanillaRendicion> getItemPlanillaRendiciones(Long idPlanilla);
}
