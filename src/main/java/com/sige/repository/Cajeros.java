package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Cajero;
import com.sige.repository.helper.cajero.CajerosQueries;

public interface Cajeros extends JpaRepository<Cajero, Long>, CajerosQueries {
   Optional<Cajero> findByDocumento(String documento);
}
