package com.meta.repository;

import com.meta.modelo.ItemPago;
import com.meta.repository.helper.itemPago.ItemPagosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPagos extends JpaRepository<ItemPago, Long>, ItemPagosQueries {
}
