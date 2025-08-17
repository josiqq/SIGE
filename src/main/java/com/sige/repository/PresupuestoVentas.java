package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.PresupuestoVenta;
import com.sige.repository.helper.presupuestoVenta.PresupuestoVentasQueries;

public interface PresupuestoVentas extends JpaRepository<PresupuestoVenta, Long>, PresupuestoVentasQueries {
}
