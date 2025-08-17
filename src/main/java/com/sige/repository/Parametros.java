package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Parametro;
import com.sige.repository.helper.parametro.ParametrosQueries;

public interface Parametros extends JpaRepository<Parametro, Long>, ParametrosQueries {
}
