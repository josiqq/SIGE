package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.StockDeposito;
import com.sige.repository.helper.stockDeposito.StockDepositosQueries;

public interface StockDepositos extends JpaRepository<StockDeposito, Long>, StockDepositosQueries {
}
