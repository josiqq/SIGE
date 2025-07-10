package com.meta.repository;

import com.meta.modelo.Timbrado;
import com.meta.repository.helper.timbrado.TimbradosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Timbrados extends JpaRepository<Timbrado, Long>, TimbradosQueries {
}
