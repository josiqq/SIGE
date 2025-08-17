package com.sige.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FacturaMapper {
   public List<FacturaDto> mapResultToList(List<Object[]> results) {
      List<FacturaDto> facturas = new ArrayList<>();

      for (Object[] row : results) {
         FacturaDto facturaDto = new FacturaDto();
         facturaDto.setId((BigInteger)row[0]);
         facturaDto.setCliente((String)row[1]);
         facturaDto.setDocumento((String)row[2]);
         facturaDto.setFactura((String)row[3]);
         facturaDto.setFecha((String)row[4]);
         facturaDto.setCondicion((String)row[5]);
         facturaDto.setVendedor((String)row[6]);
         facturaDto.setCodigo((String)row[7]);
         facturaDto.setProducto((String)row[8]);
         facturaDto.setCantidad((BigDecimal)row[9]);
         facturaDto.setPrecio((BigDecimal)row[10]);
         facturaDto.setSubTotal((BigDecimal)row[11]);
         facturaDto.setGravado((Integer)row[12]);
         facturaDto.setGravadoDies((BigDecimal)row[13]);
         facturaDto.setGravadoCinco((BigDecimal)row[14]);
         facturaDto.setImpuestoDies((BigDecimal)row[15]);
         facturaDto.setImpuestoCinco((BigDecimal)row[16]);
         facturaDto.setTimbrado((Integer)row[17]);
         facturaDto.setFechaInicio((String)row[18]);
         facturaDto.setFechaFin((String)row[19]);
         facturaDto.setPesable((Boolean)row[20]);
         facturas.add(facturaDto);
      }

      return facturas;
   }
}
