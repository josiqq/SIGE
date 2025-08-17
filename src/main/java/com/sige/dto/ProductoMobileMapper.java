package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProductoMobileMapper {
   public List<ProductoMobileDTO> mapResultDelList(List<Object[]> results) {
      List<ProductoMobileDTO> productos = new ArrayList<>();

      for (Object[] row : results) {
         ProductoMobileDTO producto = new ProductoMobileDTO();
         producto.setId((BigInteger)row[0]);
         producto.setPesable((Boolean)row[1]);
         producto.setCodigo((String)row[2]);
         producto.setNombre((String)row[3]);
         producto.setMarca((String)row[4]);
         producto.setCantidad((BigDecimal)row[5]);
         producto.setPrecio((BigDecimal)row[6]);
         producto.setCosto((BigDecimal)row[7]);
         producto.setDeposito((String)row[8]);
         producto.setFoto((String)row[9]);
         productos.add(producto);
      }

      return productos;
   }
}
