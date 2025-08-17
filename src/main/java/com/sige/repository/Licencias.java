package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Licencia;
import com.sige.repository.helper.licencia.LicenciasQueries;

public interface Licencias extends JpaRepository<Licencia, Long>, LicenciasQueries {
}
