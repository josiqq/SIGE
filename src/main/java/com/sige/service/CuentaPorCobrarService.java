package com.sige.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.dto.CuentaPorCobrarDTO;
import com.sige.model.CuentaPorCobrar;
import com.sige.model.Venta;
import com.sige.repository.CuentaPorCobrars;
import com.sige.repository.filter.CuentaPorCobrarFilter;

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
