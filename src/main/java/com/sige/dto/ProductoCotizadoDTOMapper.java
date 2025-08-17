package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProductoCotizadoDTOMapper {
   public List<ProductoCotizadoDTO> mapper(List<Object[]> results) {
      List<ProductoCotizadoDTO> items = new ArrayList<>();

      for (Object[] row : results) {
         ProductoCotizadoDTO item = new ProductoCotizadoDTO();
         item.setId((BigInteger)row[0]);
         item.setNombre((String)row[1]);
         item.setCodigo((String)row[2]);
         item.setPesable((Boolean)row[3]);
         item.setFoto((String)row[4]);
         item.setCosto((BigDecimal)row[5]);
         item.setMarca((String)row[6]);
         items.add(item);
      }

      return items;
   }
}
