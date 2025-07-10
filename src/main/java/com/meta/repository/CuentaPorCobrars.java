package com.meta.repository;

import com.meta.modelo.CuentaPorCobrar;
import com.meta.repository.helper.cuentaPorCobrar.CuentaPorCobrarsQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaPorCobrars extends JpaRepository<CuentaPorCobrar, Long>, CuentaPorCobrarsQueries {
}
