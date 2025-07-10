package com.meta.repository.helper.ajusteStock;

import com.meta.modelo.AjusteStock;
import com.meta.repository.filter.AjusteStockFilter;
import java.math.BigDecimal;
import java.util.List;

public interface AjusteStocksQueries {
   List<AjusteStock> buscarAjuste(AjusteStockFilter ajusteStockFilter);

   BigDecimal getCantidadStock(Long producto, Long deposito);
}
