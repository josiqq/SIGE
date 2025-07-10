package com.meta.repository.helper.pagoInstructor;

import com.meta.modelo.PagoInstructor;
import com.meta.repository.filter.PagoInstructorFilter;
import java.math.BigDecimal;
import java.util.List;

public interface PagoInstructoresQueries {
   List<PagoInstructor> buscarPago(PagoInstructorFilter pagoInstructorFilter);

   BigDecimal totalImporte(PagoInstructorFilter pagoInstructorFilter);
}
