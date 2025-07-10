package com.meta.repository.helper.movimientoCaja;

import com.meta.modelo.MovimientoCaja;
import com.meta.repository.filter.MovimientoCajaFilter;
import java.math.BigDecimal;
import java.util.List;

public interface MovimientoCajasQueries {
   List<MovimientoCaja> buscarMovimiento(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal totalCredito(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal totalDebito(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal saldo(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal creditoAnterior(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal debitoAnterior(MovimientoCajaFilter movimientoCajaFilter);
}
