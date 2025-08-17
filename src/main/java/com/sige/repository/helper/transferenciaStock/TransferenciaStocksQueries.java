package com.sige.repository.helper.transferenciaStock;

import java.util.List;

import com.sige.model.ItemTransferenciaStock;
import com.sige.model.TransferenciaStock;
import com.sige.repository.filter.TransferenciaStockFilter;

public interface TransferenciaStocksQueries {
   List<TransferenciaStock> getTransferencias(TransferenciaStockFilter filter);

   List<ItemTransferenciaStock> getAllItemsByTransferencia(TransferenciaStock transferencia);
}
