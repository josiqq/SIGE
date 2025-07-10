package com.meta.repository;

import com.meta.modelo.Precio;
import com.meta.repository.helper.precio.PreciosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Precios extends JpaRepository<Precio, Long>, PreciosQueries {
}
