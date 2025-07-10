package com.meta.repository.helper.itemCobro;

import com.meta.modelo.Cobro;
import com.meta.modelo.ItemCobro;
import com.meta.modelo.Venta;
import java.util.List;

public interface ItemCobrosQueries {
   List<ItemCobro> buscarPorVenta(Venta venta);

   List<ItemCobro> getItemCobroByCobro(Cobro cobro);

   List<ItemCobro> getByIdVenta(Long id);
}
