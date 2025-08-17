package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ClaseGrupal;
import com.sige.repository.helper.claseGrupal.ClaseGrupalesQueries;

public interface ClaseGrupales extends JpaRepository<ClaseGrupal, Long>, ClaseGrupalesQueries {
   Optional<ClaseGrupal> findByNombre(String nombre);
}
