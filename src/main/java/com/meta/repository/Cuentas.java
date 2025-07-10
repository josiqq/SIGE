package com.meta.repository;

import com.meta.modelo.Cuenta;
import com.meta.repository.helper.cuenta.CuentasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cuentas extends JpaRepository<Cuenta, Long>, CuentasQueries {
}
