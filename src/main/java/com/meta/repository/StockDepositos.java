package com.meta.repository;

import com.meta.modelo.StockDeposito;
import com.meta.repository.helper.stockDeposito.StockDepositosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDepositos extends JpaRepository<StockDeposito, Long>, StockDepositosQueries {
}
