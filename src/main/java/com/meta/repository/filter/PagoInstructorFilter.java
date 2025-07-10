package com.meta.repository.filter;

import com.meta.modelo.Instructor;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class PagoInstructorFilter {
   private Instructor instructor;
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaIni = LocalDate.now();
   @DateTimeFormat(
      pattern = "dd/MM/yyyy"
   )
   private LocalDate fechaFin = LocalDate.now();

   public Instructor getInstructor() {
      return this.instructor;
   }

   public void setInstructor(Instructor instructor) {
      this.instructor = instructor;
   }

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
}
