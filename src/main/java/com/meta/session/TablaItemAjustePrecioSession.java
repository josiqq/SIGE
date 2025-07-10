package com.meta.session;

import com.meta.modelo.ItemAjustePrecio;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemAjustePrecioSession {
   Set<TablaItemAjustePrecio> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal costo, BigDecimal precioMinimo, BigDecimal PrecioProducto, String uuid) {
      TablaItemAjustePrecio tablaItemAjustePrecio = this.buscarPorUUID(uuid);
      tablaItemAjustePrecio.adicionarItem(producto, costo, PrecioProducto, precioMinimo);
      this.tablas.add(tablaItemAjustePrecio);
   }

   public void modificarItemPrecioMinimo(Producto producto, BigDecimal precioMinimo, String uuid) {
      TablaItemAjustePrecio tabla = this.buscarPorUUID(uuid);
      tabla.modificarItemPrecioMinimo(producto, precioMinimo);
   }

   public void modificarItemPrecioProducto(Producto producto, BigDecimal precioProducto, String uuid) {
      TablaItemAjustePrecio tabla = this.buscarPorUUID(uuid);
      tabla.modificarItemPrecioProducto(producto, precioProducto);
   }

   public void eliminarItem(Producto producto, String uuid) {
      TablaItemAjustePrecio tabla = this.buscarPorUUID(uuid);
      tabla.eliminarItem(producto);
   }

   public List<ItemAjustePrecio> getItems(String uuid) {
      return this.buscarPorUUID(uuid).getItems();
   }

   private TablaItemAjustePrecio buscarPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemAjustePrecio(uuid));
   }
}
