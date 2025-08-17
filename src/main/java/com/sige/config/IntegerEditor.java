package com.sige.config;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class IntegerEditor extends PropertyEditorSupport {
   @Override
   public void setAsText(String text) {
      NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMAN);

      try {
         Number number = formatter.parse(text);
         Integer integer = number.intValue();
         this.setValue(integer);
      } catch (ParseException var5) {
         var5.printStackTrace();
      }
   }
}
