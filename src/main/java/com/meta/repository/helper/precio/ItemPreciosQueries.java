package com.meta.repository.helper.precio;

import com.meta.modelo.ItemPrecio;
import com.meta.modelo.Precio;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.List;

public interface ItemPreciosQueries {
   ItemPrecio buscarprecioProducto(Precio precio, Producto producto);

   List<ItemPrecio> buscarPorProductoPrecio(String nombreCodigo, Precio precio);

   ItemPrecio buscarPrecioPorCodigo(String codigo, Precio precio);

   List<ItemPrecio> buscarPorProducto(Producto producto);

   BigDecimal getPrecioProducto(Precio precio, Producto producto);
}
