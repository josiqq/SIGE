package com.sige.repository.helper.itemPago;

import java.util.List;

import com.sige.model.Compra;
import com.sige.model.ItemPago;

public interface ItemPagosQueries {
   List<ItemPago> getItemPagos(Compra compra);
}
