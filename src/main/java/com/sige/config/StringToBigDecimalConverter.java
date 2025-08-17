package com.sige.config;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {
   public BigDecimal convert(String source) {
      try {
         DecimalFormat decimalFormat = new DecimalFormat();
         decimalFormat.setParseBigDecimal(true);
         return (BigDecimal)decimalFormat.parse(source.replaceAll(",", "."));
      } catch (ParseException var3) {
         throw new IllegalArgumentException("Formato de número no válido: " + source);
      }
   }
}
