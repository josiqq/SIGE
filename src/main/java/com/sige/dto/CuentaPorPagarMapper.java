package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CuentaPorPagarMapper {
   public List<CuentaPorPagarDTO> mapearLista(List<Object[]> results) {
      List<CuentaPorPagarDTO> items = new ArrayList<>();

      for (Object[] obj : results) {
         CuentaPorPagarDTO row = new CuentaPorPagarDTO();
         row.setFecha((String)obj[0]);
         row.setProveedor((String)obj[1]);
         row.setCompra((BigInteger)obj[2]);
         row.setImporte((BigDecimal)obj[3]);
         row.setImportePagado((BigDecimal)obj[4]);
         row.setSaldo((BigDecimal)obj[5]);
         row.setVencimiento((String)obj[6]);
         items.add(row);
      }

      return items;
   }
}
