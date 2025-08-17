package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.PagoInstructor;
import com.sige.repository.helper.pagoInstructor.PagoInstructoresQueries;

public interface PagoInstructores extends JpaRepository<PagoInstructor, Long>, PagoInstructoresQueries {
}
