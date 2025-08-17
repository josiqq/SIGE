package com.sige.repository.helper.pagoInstructor;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.PagoInstructor;
import com.sige.repository.filter.PagoInstructorFilter;

public interface PagoInstructoresQueries {
   List<PagoInstructor> buscarPago(PagoInstructorFilter pagoInstructorFilter);

   BigDecimal totalImporte(PagoInstructorFilter pagoInstructorFilter);
}
