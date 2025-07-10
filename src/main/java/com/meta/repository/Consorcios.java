package com.meta.repository;

import com.meta.modelo.Consorcio;
import com.meta.repository.helper.consorcio.ConsorciosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Consorcios extends JpaRepository<Consorcio, Long>, ConsorciosQueries {
   Optional<Consorcio> findByNombre(String nombre);
}
