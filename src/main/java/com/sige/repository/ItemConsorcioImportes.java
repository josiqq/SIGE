package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemConsorcioImporte;
import com.sige.repository.helper.itemConsorcioImporte.ItemConsorcioImportesQueries;

public interface ItemConsorcioImportes extends JpaRepository<ItemConsorcioImporte, Long>, ItemConsorcioImportesQueries {
}
