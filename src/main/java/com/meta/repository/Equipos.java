package com.meta.repository;

import com.meta.modelo.Equipo;
import com.meta.repository.helper.equipo.EquiposQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Equipos extends JpaRepository<Equipo, Long>, EquiposQueries {
}
