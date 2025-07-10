package com.meta.repository;

import com.meta.modelo.Cotizacion;
import com.meta.repository.helper.cotizacion.CotizacionesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cotizaciones extends JpaRepository<Cotizacion, Long>, CotizacionesQueries {
}
