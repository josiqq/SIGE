package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProductoStockDTOMapper {
   public List<ProductoStockDTO> mapperResults(List<Object[]> results) {
      List<ProductoStockDTO> productos = new ArrayList<>();

      for (Object[] row : results) {
         ProductoStockDTO pro = new ProductoStockDTO();
         pro.setId((BigInteger)row[0]);
         pro.setCodigo((String)row[1]);
         pro.setNombre((String)row[2]);
         pro.setCosto((BigDecimal)row[3]);
         pro.setPesable((Boolean)row[4]);
         pro.setFoto((String)row[5]);
         pro.setMarca((String)row[6]);
         pro.setPrecioMinimo((BigDecimal)row[7]);
         pro.setPrecio((BigDecimal)row[8]);
         pro.setCantidad((BigDecimal)row[9]);
         pro.setDeposito((String)row[10]);
         pro.setConLote((Boolean)row[11]);
         pro.setStockMinimo((BigDecimal)row[12]);
         productos.add(pro);
      }

      return productos;
   }

   public ProductoStockDTO mapperObject(Object[] result) {
      ProductoStockDTO pro = new ProductoStockDTO();
      pro.setId((BigInteger)result[0]);
      pro.setCodigo((String)result[1]);
      pro.setNombre((String)result[2]);
      pro.setCosto((BigDecimal)result[3]);
      pro.setPesable((Boolean)result[4]);
      pro.setFoto((String)result[5]);
      pro.setMarca((String)result[6]);
      pro.setPrecioMinimo((BigDecimal)result[7]);
      pro.setPrecio((BigDecimal)result[8]);
      pro.setCantidad((BigDecimal)result[9]);
      pro.setDeposito((String)result[10]);
      pro.setConLote((Boolean)result[11]);
      pro.setStockMinimo((BigDecimal)result[12]);
      return pro;
   }
}
