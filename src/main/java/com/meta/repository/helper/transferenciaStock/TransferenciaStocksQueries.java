package com.meta.repository.helper.transferenciaStock;

import com.meta.modelo.ItemTransferenciaStock;
import com.meta.modelo.TransferenciaStock;
import com.meta.repository.filter.TransferenciaStockFilter;
import java.util.List;

public interface TransferenciaStocksQueries {
   List<TransferenciaStock> getTransferencias(TransferenciaStockFilter filter);

   List<ItemTransferenciaStock> getAllItemsByTransferencia(TransferenciaStock transferencia);
}
