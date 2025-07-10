package com.meta.repository;

import com.meta.modelo.Condicion;
import com.meta.repository.helper.condicion.CondicionesQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Condiciones extends JpaRepository<Condicion, Long>, CondicionesQueries {
   Optional<Condicion> findByNombre(String nombre);
}
