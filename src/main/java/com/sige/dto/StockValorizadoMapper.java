package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StockValorizadoMapper {
   public List<StockValorizadoDTO> mapperResults(List<Object[]> results) {
      List<StockValorizadoDTO> items = new ArrayList<>();

      for (Object[] row : results) {
         StockValorizadoDTO stock = new StockValorizadoDTO();
         stock.setId((BigInteger)row[0]);
         stock.setCodigo((String)row[1]);
         stock.setNombre((String)row[2]);
         stock.setCosto((BigDecimal)row[3]);
         stock.setPrecio((BigDecimal)row[4]);
         stock.setCantidad((BigDecimal)row[5]);
         stock.setTotalCosto((BigDecimal)row[6]);
         stock.setTotalPrecio((BigDecimal)row[7]);
         stock.setUtilidad((BigDecimal)row[8]);
         stock.setPesable((Boolean)row[9]);
         items.add(stock);
      }

      return items;
   }
}
