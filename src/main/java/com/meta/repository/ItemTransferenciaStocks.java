package com.meta.repository;

import com.meta.modelo.ItemTransferenciaStock;
import com.meta.repository.helper.itemTransferenciaStock.ItemTransferenciaStocksQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTransferenciaStocks extends JpaRepository<ItemTransferenciaStock, Long>, ItemTransferenciaStocksQueries {
}
