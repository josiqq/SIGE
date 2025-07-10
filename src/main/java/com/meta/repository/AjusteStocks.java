package com.meta.repository;

import com.meta.modelo.AjusteStock;
import com.meta.repository.helper.ajusteStock.AjusteStocksQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteStocks extends JpaRepository<AjusteStock, Long>, AjusteStocksQueries {
}
