package com.meta.repository.helper.vendedor;

import com.meta.modelo.Vendedor;
import com.meta.repository.filter.VendedorFilter;
import java.math.BigDecimal;
import java.util.List;

public interface VendedoresQueries {
   BigDecimal buscarSaldo(Long id);

   List<Vendedor> buscarVendedor(VendedorFilter vendedorFilter);

   List<Vendedor> buscarVendedoresAactivos();

   List<Vendedor> getVendedorSupervisor();
}
