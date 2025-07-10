package com.meta.repository.helper.cobroServicio;

import com.meta.modelo.CobroServicio;
import com.meta.repository.filter.CobroServicioFilter;
import java.math.BigDecimal;
import java.util.List;

public interface CobroServiciosQueries {
   List<CobroServicio> buscarCobroServicio(CobroServicioFilter cobroServicioFilter);

   BigDecimal totalImporte(CobroServicioFilter cobroSericioFilter);
}
