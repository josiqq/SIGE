package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Marcacion;
import com.sige.repository.helper.marcacion.MarcacionesQueries;

public interface Marcaciones extends JpaRepository<Marcacion, Long>, MarcacionesQueries {
}
