package com.meta.repository;

import com.meta.modelo.Licencia;
import com.meta.repository.helper.licencia.LicenciasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Licencias extends JpaRepository<Licencia, Long>, LicenciasQueries {
}
