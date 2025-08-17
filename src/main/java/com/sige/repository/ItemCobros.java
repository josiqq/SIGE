package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemCobro;
import com.sige.repository.helper.itemCobro.ItemCobrosQueries;

public interface ItemCobros extends JpaRepository<ItemCobro, Long>, ItemCobrosQueries {
}
