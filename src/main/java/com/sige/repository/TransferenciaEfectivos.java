package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.TransferenciaEfectivo;
import com.sige.repository.helper.transferenciaEfectivo.TransferenciaEfectivosQueries;

public interface TransferenciaEfectivos extends JpaRepository<TransferenciaEfectivo, Long>, TransferenciaEfectivosQueries {
}
