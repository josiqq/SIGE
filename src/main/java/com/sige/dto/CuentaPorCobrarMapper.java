package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CuentaPorCobrarMapper {
   public List<CuentaPorCobrarDTO> mapearResult(List<Object[]> results) {
      List<CuentaPorCobrarDTO> cuentas = new ArrayList<>();

      for (Object[] obj : results) {
         CuentaPorCobrarDTO cuenta = new CuentaPorCobrarDTO();
         cuenta.setFecha((String)obj[0]);
         cuenta.setCliente((String)obj[1]);
         cuenta.setVenta((BigInteger)obj[2]);
         cuenta.setImporte((BigDecimal)obj[3]);
         cuenta.setImporteCobrado((BigDecimal)obj[4]);
         cuenta.setSaldo((BigDecimal)obj[5]);
         cuentas.add(cuenta);
      }

      return cuentas;
   }
}
