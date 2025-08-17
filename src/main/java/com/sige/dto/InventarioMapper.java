package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class InventarioMapper {
   public List<InventarioDTO> mapeoResults(List<Object[]> results) {
      List<InventarioDTO> items = new ArrayList<>();

      for (Object[] row : results) {
         InventarioDTO inventario = new InventarioDTO();
         inventario.setId((BigInteger)row[0]);
         inventario.setCodigo((String)row[1]);
         inventario.setNombre((String)row[2]);
         inventario.setMarca((String)row[3]);
         inventario.setFoto((String)row[4]);
         inventario.setPesable((Boolean)row[5]);
         inventario.setCantidad((BigDecimal)row[6]);
         items.add(inventario);
      }

      return items;
   }
}
