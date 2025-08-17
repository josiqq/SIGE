package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemPrecio;
import com.sige.repository.helper.precio.ItemPreciosQueries;

public interface ItemPrecios extends JpaRepository<ItemPrecio, Long>, ItemPreciosQueries {
}
