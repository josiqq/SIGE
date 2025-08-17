package com.sige.repository.helper.venta;

import java.util.List;

import com.sige.dto.FacturaDto;
import com.sige.model.ItemVenta;
import com.sige.model.Timbrado;
import com.sige.model.Venta;
import com.sige.repository.filter.VentaFilter;
import com.sige.repository.filter.VentaMobileFilter;

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
