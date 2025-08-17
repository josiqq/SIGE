package com.sige.repository.helper.vendedor;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Vendedor;
import com.sige.repository.filter.VendedorFilter;

public interface VendedoresQueries {
   BigDecimal buscarSaldo(Long id);

   List<Vendedor> buscarVendedor(VendedorFilter vendedorFilter);

   List<Vendedor> buscarVendedoresAactivos();

   List<Vendedor> getVendedorSupervisor();
}
