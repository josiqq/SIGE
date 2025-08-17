package com.sige.repository.helper.precio;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.ItemPrecio;
import com.sige.model.Precio;
import com.sige.model.Producto;

public interface ItemPreciosQueries {
   ItemPrecio buscarprecioProducto(Precio precio, Producto producto);

   List<ItemPrecio> buscarPorProductoPrecio(String nombreCodigo, Precio precio);

   ItemPrecio buscarPrecioPorCodigo(String codigo, Precio precio);

   List<ItemPrecio> buscarPorProducto(Producto producto);

   BigDecimal getPrecioProducto(Precio precio, Producto producto);
}
