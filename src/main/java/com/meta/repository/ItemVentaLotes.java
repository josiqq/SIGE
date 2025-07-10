package com.meta.repository;

import com.meta.modelo.ItemVentaLote;
import com.meta.repository.helper.itemVentaLote.ItemVentaLotesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVentaLotes extends JpaRepository<ItemVentaLote, Long>, ItemVentaLotesQueries {
}
