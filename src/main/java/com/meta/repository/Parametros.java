package com.meta.repository;

import com.meta.modelo.Parametro;
import com.meta.repository.helper.parametro.ParametrosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Parametros extends JpaRepository<Parametro, Long>, ParametrosQueries {
}
