package com.meta.repository.helper.movimientoVendedor;

import com.meta.modelo.MovimientoVendedor;
import com.meta.modelo.Vendedor;
import java.time.LocalDate;
import java.util.List;

public interface MovimientoVendedoresQueries {
   void eliminarPorItemVenta(Long id);

   void recalcularMovimiento(Long id_vendedor);

   List<MovimientoVendedor> getMovimiento(Vendedor vendedor, LocalDate fechaDesde, LocalDate fechaHasta);
}
