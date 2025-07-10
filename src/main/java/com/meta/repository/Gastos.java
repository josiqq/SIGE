package com.meta.repository;

import com.meta.modelo.Gasto;
import com.meta.repository.helper.gasto.GastosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Gastos extends JpaRepository<Gasto, Long>, GastosQueries {
}
