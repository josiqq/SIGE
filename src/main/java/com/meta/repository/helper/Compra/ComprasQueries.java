package com.meta.repository.helper.Compra;

import com.meta.modelo.Compra;
import com.meta.modelo.ItemCompra;
import com.meta.repository.filter.CompraFilter;
import java.util.List;

public interface ComprasQueries {
   List<Compra> buscarCompra(CompraFilter compraFilter);

   List<ItemCompra> getItemCompraByCompra(Compra compra);
}
