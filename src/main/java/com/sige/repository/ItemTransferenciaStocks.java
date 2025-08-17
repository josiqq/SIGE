package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemTransferenciaStock;
import com.sige.repository.helper.itemTransferenciaStock.ItemTransferenciaStocksQueries;

public interface ItemTransferenciaStocks extends JpaRepository<ItemTransferenciaStock, Long>, ItemTransferenciaStocksQueries {
}
