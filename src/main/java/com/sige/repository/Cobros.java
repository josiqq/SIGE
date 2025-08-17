package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Cobro;
import com.sige.repository.helper.cobro.CobrosQueries;

public interface Cobros extends JpaRepository<Cobro, Long>, CobrosQueries {
}
