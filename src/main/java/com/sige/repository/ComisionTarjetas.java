package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ComisionTarjeta;
import com.sige.repository.helper.comisionTarjeta.ComisionTarjetasQueries;

public interface ComisionTarjetas extends JpaRepository<ComisionTarjeta, Long>, ComisionTarjetasQueries {
}
