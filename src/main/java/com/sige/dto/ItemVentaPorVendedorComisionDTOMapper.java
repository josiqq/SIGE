package com.sige.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemVentaPorVendedorComisionDTOMapper {
   public List<ItemVentaPorVendedorComisionDTO> mapperResults(List<Object[]> list) {
      List<ItemVentaPorVendedorComisionDTO> itemVenta = new ArrayList<>();

      for (Object[] row : list) {
         ItemVentaPorVendedorComisionDTO item = new ItemVentaPorVendedorComisionDTO();
         item.setFecha((String)row[0]);
         item.setCliente((String)row[1]);
         item.setProducto((String)row[2]);
         item.setVenta((String)row[3]);
         item.setCantidad((BigDecimal)row[4]);
         item.setTotal((BigDecimal)row[5]);
         item.setPorcComision((BigDecimal)row[6]);
         item.setComision((BigDecimal)row[7]);
         itemVenta.add(item);
      }

      return itemVenta;
   }
}
