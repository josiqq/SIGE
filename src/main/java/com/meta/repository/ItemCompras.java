package com.meta.repository;

import com.meta.modelo.ItemCompra;
import com.meta.repository.helper.Compra.ItemComprasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCompras extends JpaRepository<ItemCompra, Long>, ItemComprasQueries {
}
