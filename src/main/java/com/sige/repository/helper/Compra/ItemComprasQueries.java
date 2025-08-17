package com.sige.repository.helper.Compra;

import java.util.List;

import com.sige.model.Compra;
import com.sige.model.ItemCompra;

public interface ItemComprasQueries {
   List<ItemCompra> buscarDetalleCompra(Compra compra);
}
