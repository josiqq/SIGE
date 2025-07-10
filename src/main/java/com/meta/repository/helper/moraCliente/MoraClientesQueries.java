package com.meta.repository.helper.moraCliente;

import com.meta.modelo.MoraCliente;
import com.meta.repository.filter.MoraClienteFilter;
import java.util.List;

public interface MoraClientesQueries {
   List<MoraCliente> buscarNombreDocumento(MoraClienteFilter moraClienteFilter);
}
