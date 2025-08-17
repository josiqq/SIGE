package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Cotizacion;
import com.sige.repository.helper.cotizacion.CotizacionesQueries;

public interface Cotizaciones extends JpaRepository<Cotizacion, Long>, CotizacionesQueries {
}
