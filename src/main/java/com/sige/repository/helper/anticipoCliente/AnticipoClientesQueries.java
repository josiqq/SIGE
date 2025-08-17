package com.sige.repository.helper.anticipoCliente;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.AnticipoCliente;
import com.sige.repository.filter.AnticipoClienteFilter;

public interface AnticipoClientesQueries {
   List<AnticipoCliente> buscarAnticipo(AnticipoClienteFilter anticipoClienteFilter);

   BigDecimal totalImporte(AnticipoClienteFilter anticipoClienteFilter);
}
