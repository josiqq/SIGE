package com.meta.repository;

import com.meta.modelo.MovimientoCaja;
import com.meta.repository.helper.movimientoCaja.MovimientoCajasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoCajas extends JpaRepository<MovimientoCaja, Long>, MovimientoCajasQueries {
}
