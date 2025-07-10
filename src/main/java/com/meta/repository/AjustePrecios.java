package com.meta.repository;

import com.meta.modelo.AjustePrecio;
import com.meta.repository.helper.ajustePrecio.AjustePreciosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjustePrecios extends JpaRepository<AjustePrecio, Long>, AjustePreciosQueries {
}
