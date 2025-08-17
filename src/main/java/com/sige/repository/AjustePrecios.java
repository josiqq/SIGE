package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.AjustePrecio;
import com.sige.repository.helper.ajustePrecio.AjustePreciosQueries;

public interface AjustePrecios extends JpaRepository<AjustePrecio, Long>, AjustePreciosQueries {
}
