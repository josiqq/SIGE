package com.meta.repository.helper.cuentaPorCobrar;

import com.meta.dto.CuentaPorCobrarDTO;
import com.meta.modelo.CuentaPorCobrar;
import com.meta.modelo.Venta;
import com.meta.repository.filter.CuentaPorCobrarFilter;
import java.util.List;

public interface CuentaPorCobrarsQueries {
   CuentaPorCobrar buscarPorVenta(Venta venta);

   List<Object[]> getCuentasByCliente(Long id, Long moneda);

   List<CuentaPorCobrarDTO> getCuentaPorCobrarDTO(CuentaPorCobrarFilter cuentaPorCobrarFilter);
}
