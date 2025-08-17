package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Equipo;
import com.sige.repository.helper.equipo.EquiposQueries;

public interface Equipos extends JpaRepository<Equipo, Long>, EquiposQueries {
}
