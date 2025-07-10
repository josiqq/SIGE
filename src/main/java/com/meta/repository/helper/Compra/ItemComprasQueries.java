package com.meta.repository.helper.Compra;

import com.meta.modelo.Compra;
import com.meta.modelo.ItemCompra;
import java.util.List;

public interface ItemComprasQueries {
   List<ItemCompra> buscarDetalleCompra(Compra compra);
}
