package com.sige.repository.helper.moraCliente;

import java.util.List;

import com.sige.model.MoraCliente;
import com.sige.repository.filter.MoraClienteFilter;

public interface MoraClientesQueries {
   List<MoraCliente> buscarNombreDocumento(MoraClienteFilter moraClienteFilter);
}
