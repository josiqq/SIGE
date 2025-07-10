package com.meta.repository;

import com.meta.modelo.CuentaPorPagar;
import com.meta.repository.helper.cuentaPorPagar.CuentaPorPagarsQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaPorPagars extends JpaRepository<CuentaPorPagar, Long>, CuentaPorPagarsQueries {
}
