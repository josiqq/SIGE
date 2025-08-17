package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Membresia;
import com.sige.repository.helper.membresia.MembresiasQueries;

public interface Membresias extends JpaRepository<Membresia, Long>, MembresiasQueries {
}
