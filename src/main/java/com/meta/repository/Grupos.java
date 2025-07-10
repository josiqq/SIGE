package com.meta.repository;

import com.meta.modelo.Grupo;
import com.meta.repository.helper.grupo.GruposQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Grupos extends JpaRepository<Grupo, Long>, GruposQueries {
   Optional<Grupo> findByNombre(String nombre);
}
