package com.meta.repository.helper.stockDeposito;

import com.meta.modelo.Deposito;
import com.meta.modelo.Producto;
import com.meta.modelo.StockDeposito;
import java.math.BigDecimal;
import java.util.List;

public interface StockDepositosQueries {
   List<StockDeposito> stock(Producto producto);

   BigDecimal getCantidadByProductoDeposito(Producto producto, Deposito deposito);
}
