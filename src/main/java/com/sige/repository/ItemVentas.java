package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemVenta;
import com.sige.repository.helper.itemVenta.ItemVentasQueries;

public interface ItemVentas extends JpaRepository<ItemVenta, Long>, ItemVentasQueries {
}
