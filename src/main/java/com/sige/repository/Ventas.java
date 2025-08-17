package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Venta;
import com.sige.repository.helper.venta.VentasQueries;

public interface Ventas extends JpaRepository<Venta, Long>, VentasQueries {
}
