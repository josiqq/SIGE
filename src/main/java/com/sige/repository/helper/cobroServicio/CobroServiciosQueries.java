package com.sige.repository.helper.cobroServicio;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.CobroServicio;
import com.sige.repository.filter.CobroServicioFilter;

public interface CobroServiciosQueries {
   List<CobroServicio> buscarCobroServicio(CobroServicioFilter cobroServicioFilter);

   BigDecimal totalImporte(CobroServicioFilter cobroSericioFilter);
}
