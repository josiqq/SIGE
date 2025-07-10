package com.meta.repository;

import com.meta.modelo.Marca;
import com.meta.repository.helper.marca.MarcasQueries;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Marcas extends JpaRepository<Marca, Long>, MarcasQueries {
   Optional<Marca> findByNombre(String nombre);
}
