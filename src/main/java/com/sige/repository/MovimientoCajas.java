package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.MovimientoCaja;
import com.sige.repository.helper.movimientoCaja.MovimientoCajasQueries;

public interface MovimientoCajas extends JpaRepository<MovimientoCaja, Long>, MovimientoCajasQueries {
}
