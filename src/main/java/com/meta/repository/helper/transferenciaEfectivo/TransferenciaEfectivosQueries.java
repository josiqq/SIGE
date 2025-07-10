package com.meta.repository.helper.transferenciaEfectivo;

import com.meta.modelo.TransferenciaEfectivo;
import com.meta.repository.filter.TransferenciaEfectivoFilter;
import java.math.BigDecimal;
import java.util.List;

public interface TransferenciaEfectivosQueries {
   List<TransferenciaEfectivo> buscarTransferencia(TransferenciaEfectivoFilter transferenciaEfectivoFilter);

   BigDecimal totalImporte(TransferenciaEfectivoFilter transferenciaEfectivoFilter);
}
