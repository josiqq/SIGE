package com.meta.repository;

import com.meta.modelo.MovInstructor;
import com.meta.repository.helper.movInstructor.MovInstructoresQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovInstructores extends JpaRepository<MovInstructor, Long>, MovInstructoresQueries {
}
