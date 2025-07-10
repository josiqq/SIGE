package com.meta.repository.helper.instructor;

import com.meta.modelo.Instructor;
import com.meta.repository.filter.InstructorFilter;
import java.math.BigDecimal;
import java.util.List;

public interface InstructoresQueries {
   List<Instructor> buscarNombreDocumento(InstructorFilter instructorFilter);

   BigDecimal sadoInstructor(Long id);
}
