package com.meta.repository;

import com.meta.modelo.PresupuestoVenta;
import com.meta.repository.helper.presupuestoVenta.PresupuestoVentasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoVentas extends JpaRepository<PresupuestoVenta, Long>, PresupuestoVentasQueries {
}
