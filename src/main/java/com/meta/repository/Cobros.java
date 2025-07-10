package com.meta.repository;

import com.meta.modelo.Cobro;
import com.meta.repository.helper.cobro.CobrosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Cobros extends JpaRepository<Cobro, Long>, CobrosQueries {
}
