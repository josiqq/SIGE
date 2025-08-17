package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.AjusteStock;
import com.sige.repository.helper.ajusteStock.AjusteStocksQueries;

public interface AjusteStocks extends JpaRepository<AjusteStock, Long>, AjusteStocksQueries {
}
