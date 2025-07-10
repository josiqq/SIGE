package com.meta.repository;

import com.meta.modelo.Compra;
import com.meta.repository.helper.Compra.ComprasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Compras extends JpaRepository<Compra, Long>, ComprasQueries {
}
