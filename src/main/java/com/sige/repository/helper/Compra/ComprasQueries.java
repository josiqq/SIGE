package com.sige.repository.helper.Compra;

import java.util.List;

import com.sige.model.Compra;
import com.sige.model.ItemCompra;
import com.sige.repository.filter.CompraFilter;

public interface ComprasQueries {
   List<Compra> buscarCompra(CompraFilter compraFilter);

   List<ItemCompra> getItemCompraByCompra(Compra compra);
}
