package com.meta.repository;

import com.meta.modelo.TransferenciaStock;
import com.meta.repository.helper.transferenciaStock.TransferenciaStocksQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaStocks extends JpaRepository<TransferenciaStock, Long>, TransferenciaStocksQueries {
}
