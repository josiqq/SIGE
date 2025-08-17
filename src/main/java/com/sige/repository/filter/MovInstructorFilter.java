package com.sige.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sige.model.Instructor;

public class MovInstructorFilter {
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaIni = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   public LocalDate fechaFin = LocalDate.now();
   public Instructor instructor;

   public LocalDate getFechaIni() {
      return this.fechaIni;
   }

   public void setFechaIni(LocalDate fechaIni) {
      this.fechaIni = fechaIni;
   }

   public LocalDate getFechaFin() {
      return this.fechaFin;
   }

   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }
}
