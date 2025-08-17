package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.CobroServicio;
import com.sige.repository.helper.cobroServicio.CobroServiciosQueries;

public interface CobroServicios extends JpaRepository<CobroServicio, Long>, CobroServiciosQueries {
}
