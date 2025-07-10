package com.meta.repository;

import com.meta.modelo.ItemVenta;
import com.meta.repository.helper.itemVenta.ItemVentasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVentas extends JpaRepository<ItemVenta, Long>, ItemVentasQueries {
}
