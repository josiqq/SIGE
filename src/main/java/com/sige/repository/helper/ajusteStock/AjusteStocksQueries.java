package com.sige.repository.helper.ajusteStock;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.AjusteStock;
import com.sige.repository.filter.AjusteStockFilter;

public interface AjusteStocksQueries {
   List<AjusteStock> buscarAjuste(AjusteStockFilter ajusteStockFilter);

   BigDecimal getCantidadStock(Long producto, Long deposito);
}
