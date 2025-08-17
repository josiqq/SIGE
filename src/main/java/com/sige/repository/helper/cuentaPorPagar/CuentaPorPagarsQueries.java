package com.sige.repository.helper.cuentaPorPagar;

import java.util.List;

import com.sige.dto.CuentaPorPagarDTO;
import com.sige.model.CuentaPorPagar;
import com.sige.model.Moneda;
import com.sige.model.Proveedor;
import com.sige.repository.filter.CuentaPorPagarFilter;

public interface CuentaPorPagarsQueries {
   List<CuentaPorPagar> getCuentaPorProveedores(Proveedor proveedor, Moneda moneda);

   List<CuentaPorPagarDTO> getCuetasPorPagarDTO(CuentaPorPagarFilter cuentaPorPagarFilter);
}
