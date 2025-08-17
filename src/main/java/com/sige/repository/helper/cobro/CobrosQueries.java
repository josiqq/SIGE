package com.sige.repository.helper.cobro;

import java.util.List;

import com.sige.dto.cobro.CobroRealizadoDTO;
import com.sige.model.Cobro;
import com.sige.model.ItemCobroImporte;
import com.sige.repository.filter.CobroFilter;

public interface CobrosQueries {
   List<Cobro> getCobros(CobroFilter cobroFilter);

   List<Object[]> getArqueoCaja(String sql, Long planilla);

   List<ItemCobroImporte> getItemCobroImporte(Long id);

   List<CobroRealizadoDTO> getCobrosRealizados(CobroFilter filter);

   List<Object[]> getCobrosRealizadosMoneda(String sql);
}
