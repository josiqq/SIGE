package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ParametroCompra;
import com.sige.repository.helper.parametroCompra.ParametroComprasQueries;

public interface ParametroCompras extends JpaRepository<ParametroCompra, Long>, ParametroComprasQueries {
}
