package com.meta.repository.helper.presupuestoCompra;

import com.meta.modelo.ItemPresupuestoCompra;
import com.meta.modelo.PresupuestoCompra;
import com.meta.repository.filter.PresupuestoCompraFilter;
import java.time.LocalDate;
import java.util.List;

public interface PresupuestoComprasQueries {
   List<PresupuestoCompra> getPresupuestoCompra(PresupuestoCompraFilter filter);

   List<PresupuestoCompra> getPresupuestoByFechas(LocalDate fechaDesde, LocalDate fechaHasta);

   List<ItemPresupuestoCompra> getItemPresupuesto(Long id);
}
