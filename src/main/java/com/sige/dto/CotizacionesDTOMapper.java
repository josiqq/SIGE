package com.sige.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CotizacionesDTOMapper {
   public List<CotizacionesDTO> maperarResults(List<Object[]> results) {
      List<CotizacionesDTO> items = new ArrayList<>();

      for (Object[] obj : results) {
         CotizacionesDTO cot = new CotizacionesDTO();
         cot.setNombre((String)obj[0]);
         cot.setSigla((String)obj[1]);
         cot.setValor((BigDecimal)obj[2]);
         cot.setMultiplicar((Boolean)obj[3]);
         cot.setDividir((Boolean)obj[4]);
         cot.setDecimales((Integer)obj[5]);
         items.add(cot);
      }

      return items;
   }
}
