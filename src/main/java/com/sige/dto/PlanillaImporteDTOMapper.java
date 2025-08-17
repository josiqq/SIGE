package com.sige.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlanillaImporteDTOMapper {
   public List<PlanillaImporteDTO> mapearResults(List<Object[]> results) {
      List<PlanillaImporteDTO> items = new ArrayList<>();

      for (Object[] obj : results) {
         PlanillaImporteDTO item = new PlanillaImporteDTO();
         item.setCondicion((String)obj[0]);
         item.setMoneda((String)obj[1]);
         item.setCobro((BigDecimal)obj[2]);
         item.setApertura((BigDecimal)obj[3]);
         item.setMasVuelto((BigDecimal)obj[4]);
         item.setMenosVuelto((BigDecimal)obj[5]);
         item.setTransCredi((BigDecimal)obj[6]);
         item.setTransDebi((BigDecimal)obj[7]);
         item.setCotizacion((BigDecimal)obj[8]);
         items.add(item);
      }

      return items;
   }
}
