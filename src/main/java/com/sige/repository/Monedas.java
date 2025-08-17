package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Moneda;
import com.sige.repository.helper.moneda.MonedasQueries;

public interface Monedas extends JpaRepository<Moneda, Long>, MonedasQueries {
   Optional<Moneda> findByNombre(String nombre);
}
