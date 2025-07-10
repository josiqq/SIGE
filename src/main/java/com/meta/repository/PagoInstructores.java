package com.meta.repository;

import com.meta.modelo.PagoInstructor;
import com.meta.repository.helper.pagoInstructor.PagoInstructoresQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoInstructores extends JpaRepository<PagoInstructor, Long>, PagoInstructoresQueries {
}
