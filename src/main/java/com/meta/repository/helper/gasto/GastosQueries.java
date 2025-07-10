package com.meta.repository.helper.gasto;

import com.meta.modelo.Gasto;
import com.meta.repository.filter.GastoFilter;
import java.math.BigDecimal;
import java.util.List;

public interface GastosQueries {
   List<Gasto> BuscarGasto(GastoFilter gastoFilter);

   BigDecimal totalImporte(GastoFilter gastoFilter);

   BigDecimal gastoDelDia();
}
