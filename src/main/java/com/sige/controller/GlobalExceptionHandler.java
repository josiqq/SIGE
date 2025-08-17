package com.sige.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler({DataIntegrityViolationException.class})
   public String handleDataIntegrityViolationException(DataIntegrityViolationException e, Model model) {
      Throwable rootCause = ExceptionUtils.getRootCause(e);
      if (rootCause instanceof SQLIntegrityConstraintViolationException) {
         model.addAttribute("error", "No se pudo modificar el detalle debido a una restricción de clave externa.");
      } else {
         model.addAttribute("error", "Error al guardar los datos.");
      }

      return "error-page";
   }
}
