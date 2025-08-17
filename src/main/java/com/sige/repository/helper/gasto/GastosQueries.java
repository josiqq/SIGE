package com.sige.repository.helper.gasto;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Gasto;
import com.sige.repository.filter.GastoFilter;

public interface GastosQueries {
   List<Gasto> BuscarGasto(GastoFilter gastoFilter);

   BigDecimal totalImporte(GastoFilter gastoFilter);

   BigDecimal gastoDelDia();
}
