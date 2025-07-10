package com.meta.repository;

import com.meta.modelo.LicenciaFecha;
import com.meta.repository.helper.licenciaFecha.LicenciaFechasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenciaFechas extends JpaRepository<LicenciaFecha, Long>, LicenciaFechasQueries {
}
