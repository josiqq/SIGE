package com.sige.repository.helper.itemVenta;

import java.time.LocalDate;
import java.util.List;

import com.sige.dto.ItemVentaPorVendedorComisionDTO;
import com.sige.model.ItemVenta;
import com.sige.model.Venta;
import com.sige.repository.filter.ItemVentaFilter;

public interface ItemVentasQueries {
   List<ItemVenta> buscarPorVenta(Venta venta);

   List<Object[]> totalesVenta(ItemVentaFilter itemVentaFilter);

   List<ItemVentaPorVendedorComisionDTO> gentVentaByVendedorComision(Long vendedor, LocalDate fechaDesde, LocalDate fechaHasta);

   void eliminarItemVenta(Long idVenta);
}
