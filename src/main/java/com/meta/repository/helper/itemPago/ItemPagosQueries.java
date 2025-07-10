package com.meta.repository.helper.itemPago;

import com.meta.modelo.Compra;
import com.meta.modelo.ItemPago;
import java.util.List;

public interface ItemPagosQueries {
   List<ItemPago> getItemPagos(Compra compra);
}
