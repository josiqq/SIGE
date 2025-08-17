package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Grupo;
import com.sige.repository.helper.grupo.GruposQueries;

public interface Grupos extends JpaRepository<Grupo, Long>, GruposQueries {
   Optional<Grupo> findByNombre(String nombre);
}
