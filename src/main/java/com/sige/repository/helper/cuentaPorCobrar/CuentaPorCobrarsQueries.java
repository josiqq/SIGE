package com.sige.repository.helper.cuentaPorCobrar;

import java.util.List;

import com.sige.dto.CuentaPorCobrarDTO;
import com.sige.model.CuentaPorCobrar;
import com.sige.model.Venta;
import com.sige.repository.filter.CuentaPorCobrarFilter;

public interface CuentaPorCobrarsQueries {
   CuentaPorCobrar buscarPorVenta(Venta venta);

   List<Object[]> getCuentasByCliente(Long id, Long moneda);

   List<CuentaPorCobrarDTO> getCuentaPorCobrarDTO(CuentaPorCobrarFilter cuentaPorCobrarFilter);
}
