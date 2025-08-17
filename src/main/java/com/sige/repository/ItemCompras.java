package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemCompra;
import com.sige.repository.helper.Compra.ItemComprasQueries;

public interface ItemCompras extends JpaRepository<ItemCompra, Long>, ItemComprasQueries {
}
