package com.meta.repository;

import com.meta.modelo.Membresia;
import com.meta.repository.helper.membresia.MembresiasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Membresias extends JpaRepository<Membresia, Long>, MembresiasQueries {
}
