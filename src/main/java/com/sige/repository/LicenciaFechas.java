package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.LicenciaFecha;
import com.sige.repository.helper.licenciaFecha.LicenciaFechasQueries;

public interface LicenciaFechas extends JpaRepository<LicenciaFecha, Long>, LicenciaFechasQueries {
}
