package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.CuentaPorCobrar;
import com.sige.repository.helper.cuentaPorCobrar.CuentaPorCobrarsQueries;

public interface CuentaPorCobrars extends JpaRepository<CuentaPorCobrar, Long>, CuentaPorCobrarsQueries {
}
