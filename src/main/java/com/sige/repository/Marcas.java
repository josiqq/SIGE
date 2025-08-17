package com.sige.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Marca;
import com.sige.repository.helper.marca.MarcasQueries;

public interface Marcas extends JpaRepository<Marca, Long>, MarcasQueries {
   Optional<Marca> findByNombre(String nombre);
}
