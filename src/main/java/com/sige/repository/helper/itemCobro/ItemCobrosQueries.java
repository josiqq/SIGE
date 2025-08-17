package com.sige.repository.helper.itemCobro;

import java.util.List;

import com.sige.model.Cobro;
import com.sige.model.ItemCobro;
import com.sige.model.Venta;

public interface ItemCobrosQueries {
   List<ItemCobro> buscarPorVenta(Venta venta);

   List<ItemCobro> getItemCobroByCobro(Cobro cobro);

   List<ItemCobro> getByIdVenta(Long id);
}
