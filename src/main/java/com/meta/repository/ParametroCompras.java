package com.meta.repository;

import com.meta.modelo.ParametroCompra;
import com.meta.repository.helper.parametroCompra.ParametroComprasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametroCompras extends JpaRepository<ParametroCompra, Long>, ParametroComprasQueries {
}
