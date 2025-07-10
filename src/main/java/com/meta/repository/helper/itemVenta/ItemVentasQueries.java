package com.meta.repository.helper.itemVenta;

import com.meta.dto.ItemVentaPorVendedorComisionDTO;
import com.meta.modelo.ItemVenta;
import com.meta.modelo.Venta;
import com.meta.repository.filter.ItemVentaFilter;
import java.time.LocalDate;
import java.util.List;

public interface ItemVentasQueries {
   List<ItemVenta> buscarPorVenta(Venta venta);

   List<Object[]> totalesVenta(ItemVentaFilter itemVentaFilter);

   List<ItemVentaPorVendedorComisionDTO> gentVentaByVendedorComision(Long vendedor, LocalDate fechaDesde, LocalDate fechaHasta);

   void eliminarItemVenta(Long idVenta);
}
