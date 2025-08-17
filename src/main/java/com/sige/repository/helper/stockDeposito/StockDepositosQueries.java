package com.sige.repository.helper.stockDeposito;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Deposito;
import com.sige.model.Producto;
import com.sige.model.StockDeposito;

public interface StockDepositosQueries {
   List<StockDeposito> stock(Producto producto);

   BigDecimal getCantidadByProductoDeposito(Producto producto, Deposito deposito);
}
