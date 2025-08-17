package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.MovInstructor;
import com.sige.repository.helper.movInstructor.MovInstructoresQueries;

public interface MovInstructores extends JpaRepository<MovInstructor, Long>, MovInstructoresQueries {
}
