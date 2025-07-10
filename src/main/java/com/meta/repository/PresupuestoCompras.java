package com.meta.repository;

import com.meta.modelo.PresupuestoCompra;
import com.meta.repository.helper.presupuestoCompra.PresupuestoComprasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresupuestoCompras extends JpaRepository<PresupuestoCompra, Long>, PresupuestoComprasQueries {
}
