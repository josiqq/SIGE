package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemPago;
import com.sige.repository.helper.itemPago.ItemPagosQueries;

public interface ItemPagos extends JpaRepository<ItemPago, Long>, ItemPagosQueries {
}
