package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ParametroVenta;
import com.sige.repository.helper.parametroVenta.ParametroVentasQueries;

public interface ParametroVentas extends JpaRepository<ParametroVenta, Long>, ParametroVentasQueries {
}
