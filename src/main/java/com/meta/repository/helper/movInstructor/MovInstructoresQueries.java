package com.meta.repository.helper.movInstructor;

import com.meta.modelo.MovInstructor;
import com.meta.repository.filter.MovInstructorFilter;
import java.math.BigDecimal;
import java.util.List;

public interface MovInstructoresQueries {
   List<MovInstructor> buscarMovInstructor(MovInstructorFilter movInstructorFilter);

   List<MovInstructor> buscarPorInstructor(MovInstructorFilter movInstructorFilter);

   BigDecimal totalMovInstructor(MovInstructorFilter movInstructorFilter);

   BigDecimal totalPorInstructor(MovInstructorFilter movInstructorFilter);
}
