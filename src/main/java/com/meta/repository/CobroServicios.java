package com.meta.repository;

import com.meta.modelo.CobroServicio;
import com.meta.repository.helper.cobroServicio.CobroServiciosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobroServicios extends JpaRepository<CobroServicio, Long>, CobroServiciosQueries {
}
