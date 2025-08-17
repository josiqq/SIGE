package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemAjusteLote;
import com.sige.repository.helper.itemAjusteLote.ItemAjusteLotesQueries;

public interface ItemAjusteLotes extends JpaRepository<ItemAjusteLote, Long>, ItemAjusteLotesQueries {
}
