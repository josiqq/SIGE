package com.meta.repository;

import com.meta.modelo.ItemCobro;
import com.meta.repository.helper.itemCobro.ItemCobrosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCobros extends JpaRepository<ItemCobro, Long>, ItemCobrosQueries {
}
