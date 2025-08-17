package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Lote;
import com.sige.repository.helper.lote.LotesQueries;

public interface Lotes extends JpaRepository<Lote, Long>, LotesQueries {
}
