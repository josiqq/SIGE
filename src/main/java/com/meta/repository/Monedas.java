package com.meta.repository;

import com.meta.modelo.Moneda;
import com.meta.repository.helper.moneda.MonedasQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Monedas extends JpaRepository<Moneda, Long>, MonedasQueries {
   Optional<Moneda> findByNombre(String nombre);
}
