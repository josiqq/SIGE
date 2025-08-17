package com.sige.config;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class BigDecimalEditor extends PropertyEditorSupport {
   @Override
   public void setAsText(String text) {
      NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMAN);

      try {
         Number number = formatter.parse(text);
         BigDecimal bigDecimal = BigDecimal.valueOf(number.doubleValue());
         this.setValue(bigDecimal);
      } catch (ParseException var5) {
      }
   }
}
