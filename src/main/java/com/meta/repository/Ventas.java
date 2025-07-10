package com.meta.repository;

import com.meta.modelo.Venta;
import com.meta.repository.helper.venta.VentasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ventas extends JpaRepository<Venta, Long>, VentasQueries {
}
