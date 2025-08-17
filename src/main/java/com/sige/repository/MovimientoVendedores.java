package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.MovimientoVendedor;
import com.sige.repository.helper.movimientoVendedor.MovimientoVendedoresQueries;

public interface MovimientoVendedores extends JpaRepository<MovimientoVendedor, Long>, MovimientoVendedoresQueries {
}
