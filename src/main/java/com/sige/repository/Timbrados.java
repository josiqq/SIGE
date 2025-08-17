package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Timbrado;
import com.sige.repository.helper.timbrado.TimbradosQueries;

public interface Timbrados extends JpaRepository<Timbrado, Long>, TimbradosQueries {
}
