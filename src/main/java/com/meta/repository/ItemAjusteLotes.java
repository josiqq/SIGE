package com.meta.repository;

import com.meta.modelo.ItemAjusteLote;
import com.meta.repository.helper.itemAjusteLote.ItemAjusteLotesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAjusteLotes extends JpaRepository<ItemAjusteLote, Long>, ItemAjusteLotesQueries {
}
