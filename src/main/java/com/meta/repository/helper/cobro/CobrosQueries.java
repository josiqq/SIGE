package com.meta.repository.helper.cobro;

import com.meta.dto.cobro.CobroRealizadoDTO;
import com.meta.modelo.Cobro;
import com.meta.modelo.ItemCobroImporte;
import com.meta.repository.filter.CobroFilter;
import java.util.List;

public interface CobrosQueries {
   List<Cobro> getCobros(CobroFilter cobroFilter);

   List<Object[]> getArqueoCaja(String sql, Long planilla);

   List<ItemCobroImporte> getItemCobroImporte(Long id);

   List<CobroRealizadoDTO> getCobrosRealizados(CobroFilter filter);

   List<Object[]> getCobrosRealizadosMoneda(String sql);
}
