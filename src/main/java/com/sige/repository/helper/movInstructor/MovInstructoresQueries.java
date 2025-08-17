package com.sige.repository.helper.movInstructor;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.MovInstructor;
import com.sige.repository.filter.MovInstructorFilter;

public interface MovInstructoresQueries {
   List<MovInstructor> buscarMovInstructor(MovInstructorFilter movInstructorFilter);

   List<MovInstructor> buscarPorInstructor(MovInstructorFilter movInstructorFilter);

   BigDecimal totalMovInstructor(MovInstructorFilter movInstructorFilter);

   BigDecimal totalPorInstructor(MovInstructorFilter movInstructorFilter);
}
