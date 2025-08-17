package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Cuenta;
import com.sige.repository.helper.cuenta.CuentasQueries;

public interface Cuentas extends JpaRepository<Cuenta, Long>, CuentasQueries {
}
