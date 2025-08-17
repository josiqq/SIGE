package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.AjusteLote;
import com.sige.repository.helper.ajusteLote.AjusteLotesQueries;

public interface AjusteLotes extends JpaRepository<AjusteLote, Long>, AjusteLotesQueries {
}
