package com.meta.service;

import com.meta.dto.CuentaPorCobrarDTO;
import com.meta.modelo.CuentaPorCobrar;
import com.meta.modelo.Venta;
import com.meta.repository.CuentaPorCobrars;
import com.meta.repository.filter.CuentaPorCobrarFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaPorCobrarService {
   @Autowired
   private CuentaPorCobrars cuentaPorCobrars;

   public CuentaPorCobrar buscarPorVenta(Venta venta) {
      new CuentaPorCobrar();

      CuentaPorCobrar cuentaPorCobrar;
      try {
         cuentaPorCobrar = this.cuentaPorCobrars.buscarPorVenta(venta);
      } catch (Exception var4) {
         cuentaPorCobrar = new CuentaPorCobrar();
      }

      return cuentaPorCobrar;
   }

   public List<Object[]> getCuentasByCliente(Long id, Long moneda) {
      return this.cuentaPorCobrars.getCuentasByCliente(id, moneda);
   }

   public List<CuentaPorCobrarDTO> getCuentaPorCobrar(CuentaPorCobrarFilter cuentaPorCobrarFilter) {
      return this.cuentaPorCobrars.getCuentaPorCobrarDTO(cuentaPorCobrarFilter);
   }
}
