package com.sige.repository.helper.transferenciaEfectivo;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.TransferenciaEfectivo;
import com.sige.repository.filter.TransferenciaEfectivoFilter;

public interface TransferenciaEfectivosQueries {
   List<TransferenciaEfectivo> buscarTransferencia(TransferenciaEfectivoFilter transferenciaEfectivoFilter);

   BigDecimal totalImporte(TransferenciaEfectivoFilter transferenciaEfectivoFilter);
}
