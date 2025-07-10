package com.meta.repository.helper.ajustePrecio;

import com.meta.modelo.AjustePrecio;
import com.meta.modelo.ItemAjustePrecio;
import com.meta.repository.filter.AjustePrecioFilter;
import java.util.List;

public interface AjustePreciosQueries {
   List<AjustePrecio> buscarAjustePrecio(AjustePrecioFilter ajustePrecioFilter);

   List<ItemAjustePrecio> buscarItemAjustePrecio(AjustePrecioFilter filter);
}
