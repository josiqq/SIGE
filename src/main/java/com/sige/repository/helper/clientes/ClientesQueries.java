package com.sige.repository.helper.clientes;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Cliente;
import com.sige.repository.filter.ClienteFilter;

public interface ClientesQueries {
   List<Cliente> buscarPorNombreODocumento(String nombreDocumento);

   Long cantidadCliente();

   List<Cliente> buscarCliente(ClienteFilter clienteFilter);

   BigDecimal buscarSaldo(Long id);

   List<Cliente> getClientesByNombreDocumento(String nombreDocumento);
}
