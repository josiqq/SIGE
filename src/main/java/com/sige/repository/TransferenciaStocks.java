package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.TransferenciaStock;
import com.sige.repository.helper.transferenciaStock.TransferenciaStocksQueries;

public interface TransferenciaStocks extends JpaRepository<TransferenciaStock, Long>, TransferenciaStocksQueries {
}
