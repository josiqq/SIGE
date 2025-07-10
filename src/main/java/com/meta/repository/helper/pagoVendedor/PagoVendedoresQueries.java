package com.meta.repository.helper.pagoVendedor;

import com.meta.modelo.PagoVendedor;
import com.meta.repository.filter.PagoVendedorFilter;
import java.math.BigDecimal;
import java.util.List;

public interface PagoVendedoresQueries {
   List<PagoVendedor> buscarPagoVendedores(PagoVendedorFilter pagoVendedorFilter);

   BigDecimal totalPagoVendedor(PagoVendedorFilter pagoVendedorFilter);
}
