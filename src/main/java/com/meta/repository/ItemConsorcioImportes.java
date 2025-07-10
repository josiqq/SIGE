package com.meta.repository;

import com.meta.modelo.ItemConsorcioImporte;
import com.meta.repository.helper.itemConsorcioImporte.ItemConsorcioImportesQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemConsorcioImportes extends JpaRepository<ItemConsorcioImporte, Long>, ItemConsorcioImportesQueries {
}
