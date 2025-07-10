package com.meta.repository.helper.clientes;

import com.meta.modelo.Cliente;
import com.meta.repository.filter.ClienteFilter;
import java.math.BigDecimal;
import java.util.List;

public interface ClientesQueries {
   List<Cliente> buscarPorNombreODocumento(String nombreDocumento);

   Long cantidadCliente();

   List<Cliente> buscarCliente(ClienteFilter clienteFilter);

   BigDecimal buscarSaldo(Long id);

   List<Cliente> getClientesByNombreDocumento(String nombreDocumento);
}
