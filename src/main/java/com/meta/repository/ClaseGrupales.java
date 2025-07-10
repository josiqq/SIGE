package com.meta.repository;

import com.meta.modelo.ClaseGrupal;
import com.meta.repository.helper.claseGrupal.ClaseGrupalesQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseGrupales extends JpaRepository<ClaseGrupal, Long>, ClaseGrupalesQueries {
   Optional<ClaseGrupal> findByNombre(String nombre);
}
