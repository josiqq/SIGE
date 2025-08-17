package com.sige.repository.helper.movimientoVendedor;

import java.time.LocalDate;
import java.util.List;

import com.sige.model.MovimientoVendedor;
import com.sige.model.Vendedor;

public interface MovimientoVendedoresQueries {
   void eliminarPorItemVenta(Long id);

   void recalcularMovimiento(Long id_vendedor);

   List<MovimientoVendedor> getMovimiento(Vendedor vendedor, LocalDate fechaDesde, LocalDate fechaHasta);
}
