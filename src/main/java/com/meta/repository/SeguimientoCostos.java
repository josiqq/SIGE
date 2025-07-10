package com.meta.repository;

import com.meta.modelo.SeguimientoCosto;
import com.meta.repository.helper.seguimientoCosto.SeguimientoCostosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguimientoCostos extends JpaRepository<SeguimientoCosto, Long>, SeguimientoCostosQueries {
}
