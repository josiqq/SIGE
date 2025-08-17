package com.sige.config;

import java.beans.PropertyEditorSupport;
import java.time.format.DateTimeFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

public class LocalDateEditor extends PropertyEditorSupport {
   public void SetAsText(String text) {
      DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
      dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   }
}
