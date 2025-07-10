package com.meta.repository;

import com.meta.modelo.ItemPrecio;
import com.meta.repository.helper.precio.ItemPreciosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPrecios extends JpaRepository<ItemPrecio, Long>, ItemPreciosQueries {
}
