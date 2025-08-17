package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Consorcio;
import com.sige.repository.helper.consorcio.ConsorciosQueries;

public interface Consorcios extends JpaRepository<Consorcio, Long>, ConsorciosQueries {
   Optional<Consorcio> findByNombre(String nombre);
}
