package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.SeguimientoCosto;
import com.sige.repository.helper.seguimientoCosto.SeguimientoCostosQueries;

public interface SeguimientoCostos extends JpaRepository<SeguimientoCosto, Long>, SeguimientoCostosQueries {
}
