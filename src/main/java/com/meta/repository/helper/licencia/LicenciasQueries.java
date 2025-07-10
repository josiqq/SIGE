package com.meta.repository.helper.licencia;

import com.meta.modelo.Licencia;
import java.time.LocalDate;

public interface LicenciasQueries {
   LocalDate buscarUltimaFecha();

   Licencia getLicenciaByFecha(LocalDate fecha);

   Long buscarUltimoId();
}
