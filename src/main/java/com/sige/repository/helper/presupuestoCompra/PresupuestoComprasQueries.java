package com.sige.repository.helper.presupuestoCompra;

import java.time.LocalDate;
import java.util.List;

import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.PresupuestoCompra;
import com.sige.repository.filter.PresupuestoCompraFilter;

public interface PresupuestoComprasQueries {
   List<PresupuestoCompra> getPresupuestoCompra(PresupuestoCompraFilter filter);

   List<PresupuestoCompra> getPresupuestoByFechas(LocalDate fechaDesde, LocalDate fechaHasta);

   List<ItemPresupuestoCompra> getItemPresupuesto(Long id);
}
