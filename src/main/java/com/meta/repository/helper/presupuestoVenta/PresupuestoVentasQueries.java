package com.meta.repository.helper.presupuestoVenta;

import com.meta.modelo.ItemPresupuestoVenta;
import com.meta.modelo.PresupuestoVenta;
import com.meta.modelo.Vendedor;
import com.meta.repository.filter.PresupuestoVentaFilter;
import java.time.LocalDate;
import java.util.List;

public interface PresupuestoVentasQueries {
   List<PresupuestoVenta> getPresupuestoVentas(PresupuestoVentaFilter presupuestoVentaFilter);

   List<PresupuestoVenta> BuscarPresupuestoVenta(LocalDate fechaDesde, LocalDate fechaHasta, Vendedor vendedor);

   List<ItemPresupuestoVenta> getItemPresupuesto(PresupuestoVenta presupuestoVenta);
}
