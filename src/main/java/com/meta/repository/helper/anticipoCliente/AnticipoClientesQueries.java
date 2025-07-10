package com.meta.repository.helper.anticipoCliente;

import com.meta.modelo.AnticipoCliente;
import com.meta.repository.filter.AnticipoClienteFilter;
import java.math.BigDecimal;
import java.util.List;

public interface AnticipoClientesQueries {
   List<AnticipoCliente> buscarAnticipo(AnticipoClienteFilter anticipoClienteFilter);

   BigDecimal totalImporte(AnticipoClienteFilter anticipoClienteFilter);
}
