package com.meta.repository.helper.venta;

import com.meta.dto.FacturaDto;
import com.meta.modelo.ItemVenta;
import com.meta.modelo.Timbrado;
import com.meta.modelo.Venta;
import com.meta.repository.filter.VentaFilter;
import com.meta.repository.filter.VentaMobileFilter;
import java.util.List;

public interface VentasQueries {
   List<Venta> buscarVenta(VentaFilter ventaFilter);

   List<Object[]> getVentaPorProducto(VentaFilter vevntaFilter);

   List<FacturaDto> getFactura(Long id);

   Integer getNroFactura(Timbrado timbrado);

   void updateNroFactura(Timbrado timbrado, Venta venta, Integer nroFactura);

   void updateImpreso(Long id);

   List<Venta> getVentaMobile(VentaMobileFilter ventaMobileFilter);

   List<ItemVenta> getItemVentaByVenta(Venta venta);

   List<Venta> getVentaNc(VentaFilter ventaFilter);
}
