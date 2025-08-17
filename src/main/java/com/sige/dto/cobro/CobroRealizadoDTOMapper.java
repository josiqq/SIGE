package com.sige.dto.cobro;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CobroRealizadoDTOMapper {
   public List<CobroRealizadoDTO> mapper(List<Object[]> results) {
      List<CobroRealizadoDTO> items = new ArrayList<>();

      for (Object[] obj : results) {
         CobroRealizadoDTO item = new CobroRealizadoDTO();
         item.setCobro((BigInteger)obj[0]);
         item.setFecha((String)obj[1]);
         item.setCuenta((String)obj[2]);
         item.setCliente((String)obj[3]);
         item.setCondicion((String)obj[4]);
         item.setMoneda((String)obj[5]);
         item.setSigla((String)obj[6]);
         item.setImporte((BigDecimal)obj[7]);
         items.add(item);
      }

      return items;
   }
}
