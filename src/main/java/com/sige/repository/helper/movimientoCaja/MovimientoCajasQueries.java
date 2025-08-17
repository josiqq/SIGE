package com.sige.repository.helper.movimientoCaja;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.MovimientoCaja;
import com.sige.repository.filter.MovimientoCajaFilter;

public interface MovimientoCajasQueries {
   List<MovimientoCaja> buscarMovimiento(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal totalCredito(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal totalDebito(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal saldo(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal creditoAnterior(MovimientoCajaFilter movimientoCajaFilter);

   BigDecimal debitoAnterior(MovimientoCajaFilter movimientoCajaFilter);
}
