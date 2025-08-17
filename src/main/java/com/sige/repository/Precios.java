package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Precio;
import com.sige.repository.helper.precio.PreciosQueries;

public interface Precios extends JpaRepository<Precio, Long>, PreciosQueries {
}
