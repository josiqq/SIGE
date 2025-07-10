package com.meta.repository;

import com.meta.modelo.AjusteLote;
import com.meta.repository.helper.ajusteLote.AjusteLotesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteLotes extends JpaRepository<AjusteLote, Long>, AjusteLotesQueries {
}
