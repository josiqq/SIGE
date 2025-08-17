package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Gasto;
import com.sige.repository.helper.gasto.GastosQueries;

public interface Gastos extends JpaRepository<Gasto, Long>, GastosQueries {
}
