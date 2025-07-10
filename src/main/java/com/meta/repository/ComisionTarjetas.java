package com.meta.repository;

import com.meta.modelo.ComisionTarjeta;
import com.meta.repository.helper.comisionTarjeta.ComisionTarjetasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComisionTarjetas extends JpaRepository<ComisionTarjeta, Long>, ComisionTarjetasQueries {
}
