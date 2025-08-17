package com.sige.repository.helper.cotizacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sige.dto.CotizacionesDTO;
import com.sige.model.Cotizacion;
import com.sige.model.Moneda;
import com.sige.repository.filter.CotizacionFilter;

public interface CotizacionesQueries {
   Optional<Cotizacion> getCotizaciones(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino);

   Cotizacion getCotizacionByFecha(LocalDate fecha, Moneda monedaOrigen, Moneda monedaDestino);

   LocalDate maximaFecha(Moneda monedaOrigen, Moneda monedaDestino);

   BigDecimal getValorCotizacion(LocalDate fecha, Long monedaOrigen, Long monedaDestino);

   List<Cotizacion> getAllCotizaciones(CotizacionFilter cotizacionFilter);

   List<CotizacionesDTO> getCotizacionesDTO(LocalDate fecha, Long monedaOrigen);

   BigDecimal fCotizacion(Long monedaOrigen, Long monedaDestino, LocalDate fecha);

   Long getCantidadCotizaciones();

   List<Cotizacion> getAllCotizacionCurDate();

   BigDecimal fCotizar(Long monedaOrigen, Long monedaDestino, LocalDate fecha, BigDecimal importe, BigDecimal cotizacion);
}
