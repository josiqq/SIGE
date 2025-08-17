package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.PresupuestoCompra;
import com.sige.repository.helper.presupuestoCompra.PresupuestoComprasQueries;

public interface PresupuestoCompras extends JpaRepository<PresupuestoCompra, Long>, PresupuestoComprasQueries {
}
