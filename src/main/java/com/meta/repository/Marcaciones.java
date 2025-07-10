package com.meta.repository;

import com.meta.modelo.Marcacion;
import com.meta.repository.helper.marcacion.MarcacionesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Marcaciones extends JpaRepository<Marcacion, Long>, MarcacionesQueries {
}
