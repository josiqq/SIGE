package com.sige.repository.helper.presupuestoVenta;

import java.time.LocalDate;
import java.util.List;

import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.PresupuestoVenta;
import com.sige.model.Vendedor;
import com.sige.repository.filter.PresupuestoVentaFilter;

public interface PresupuestoVentasQueries {
   List<PresupuestoVenta> getPresupuestoVentas(PresupuestoVentaFilter presupuestoVentaFilter);

   List<PresupuestoVenta> BuscarPresupuestoVenta(LocalDate fechaDesde, LocalDate fechaHasta, Vendedor vendedor);

   List<ItemPresupuestoVenta> getItemPresupuesto(PresupuestoVenta presupuestoVenta);
}
