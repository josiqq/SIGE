package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Condicion;
import com.sige.repository.helper.condicion.CondicionesQueries;

public interface Condiciones extends JpaRepository<Condicion, Long>, CondicionesQueries {
   Optional<Condicion> findByNombre(String nombre);
}
