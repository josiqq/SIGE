package com.meta.repository;

import com.meta.modelo.ParametroVenta;
import com.meta.repository.helper.parametroVenta.ParametroVentasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametroVentas extends JpaRepository<ParametroVenta, Long>, ParametroVentasQueries {
}
