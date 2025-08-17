package com.sige.repository.helper.pagoVendedor;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.PagoVendedor;
import com.sige.repository.filter.PagoVendedorFilter;

public interface PagoVendedoresQueries {
   List<PagoVendedor> buscarPagoVendedores(PagoVendedorFilter pagoVendedorFilter);

   BigDecimal totalPagoVendedor(PagoVendedorFilter pagoVendedorFilter);
}
