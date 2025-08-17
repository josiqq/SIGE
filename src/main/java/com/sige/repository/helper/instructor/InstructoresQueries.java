package com.sige.repository.helper.instructor;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Instructor;
import com.sige.repository.filter.InstructorFilter;

public interface InstructoresQueries {
   List<Instructor> buscarNombreDocumento(InstructorFilter instructorFilter);

   BigDecimal sadoInstructor(Long id);
}
