package com.meta.repository;

import com.meta.modelo.Lote;
import com.meta.repository.helper.lote.LotesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Lotes extends JpaRepository<Lote, Long>, LotesQueries {
}
