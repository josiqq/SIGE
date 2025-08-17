package com.sige.repository.helper.licencia;

import java.time.LocalDate;

import com.sige.model.Licencia;

public interface LicenciasQueries {
   LocalDate buscarUltimaFecha();

   Licencia getLicenciaByFecha(LocalDate fecha);

   Long buscarUltimoId();
}
