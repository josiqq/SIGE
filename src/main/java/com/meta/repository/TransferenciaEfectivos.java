package com.meta.repository;

import com.meta.modelo.TransferenciaEfectivo;
import com.meta.repository.helper.transferenciaEfectivo.TransferenciaEfectivosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaEfectivos extends JpaRepository<TransferenciaEfectivo, Long>, TransferenciaEfectivosQueries {
}
