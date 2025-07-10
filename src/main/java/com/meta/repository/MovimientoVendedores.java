package com.meta.repository;

import com.meta.modelo.MovimientoVendedor;
import com.meta.repository.helper.movimientoVendedor.MovimientoVendedoresQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoVendedores extends JpaRepository<MovimientoVendedor, Long>, MovimientoVendedoresQueries {
}
