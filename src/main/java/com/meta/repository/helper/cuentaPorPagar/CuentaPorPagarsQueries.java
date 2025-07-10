package com.meta.repository.helper.cuentaPorPagar;

import com.meta.dto.CuentaPorPagarDTO;
import com.meta.modelo.CuentaPorPagar;
import com.meta.modelo.Moneda;
import com.meta.modelo.Proveedor;
import com.meta.repository.filter.CuentaPorPagarFilter;
import java.util.List;

public interface CuentaPorPagarsQueries {
   List<CuentaPorPagar> getCuentaPorProveedores(Proveedor proveedor, Moneda moneda);

   List<CuentaPorPagarDTO> getCuetasPorPagarDTO(CuentaPorPagarFilter cuentaPorPagarFilter);
}
