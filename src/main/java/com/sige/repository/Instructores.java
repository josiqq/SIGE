package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Instructor;
import com.sige.repository.helper.instructor.InstructoresQueries;

public interface Instructores extends JpaRepository<Instructor, Long>, InstructoresQueries {
   Optional<Instructor> findByDocumento(String documento);
}
