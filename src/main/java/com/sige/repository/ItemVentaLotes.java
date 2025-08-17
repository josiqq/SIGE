package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemVentaLote;
import com.sige.repository.helper.itemVentaLote.ItemVentaLotesQueries;

public interface ItemVentaLotes extends JpaRepository<ItemVentaLote, Long>, ItemVentaLotesQueries {
}
