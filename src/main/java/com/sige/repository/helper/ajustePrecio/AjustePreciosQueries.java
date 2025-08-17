package com.sige.repository.helper.ajustePrecio;

import java.util.List;

import com.sige.model.AjustePrecio;
import com.sige.model.ItemAjustePrecio;
import com.sige.repository.filter.AjustePrecioFilter;

public interface AjustePreciosQueries {
   List<AjustePrecio> buscarAjustePrecio(AjustePrecioFilter ajustePrecioFilter);

   List<ItemAjustePrecio> buscarItemAjustePrecio(AjustePrecioFilter filter);
}
