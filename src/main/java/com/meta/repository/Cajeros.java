package com.meta.repository;

import com.meta.modelo.Cajero;
import com.meta.repository.helper.cajero.CajerosQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cajeros extends JpaRepository<Cajero, Long>, CajerosQueries {
   Optional<Cajero> findByDocumento(String documento);
}
