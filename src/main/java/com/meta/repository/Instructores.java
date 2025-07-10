package com.meta.repository;

import com.meta.modelo.Instructor;
import com.meta.repository.helper.instructor.InstructoresQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Instructores extends JpaRepository<Instructor, Long>, InstructoresQueries {
   Optional<Instructor> findByDocumento(String documento);
}
