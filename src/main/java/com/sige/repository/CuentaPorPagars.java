package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.CuentaPorPagar;
import com.sige.repository.helper.cuentaPorPagar.CuentaPorPagarsQueries;

public interface CuentaPorPagars extends JpaRepository<CuentaPorPagar, Long>, CuentaPorPagarsQueries {
}
