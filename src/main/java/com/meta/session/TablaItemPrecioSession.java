package com.meta.session;

import com.meta.modelo.ItemPrecio;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemPrecioSession {
   private Set<TablaItemPrecio> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal costo, BigDecimal precioProducto, String uuid) {
      TablaItemPrecio tabla = this.buscarPorUUID(uuid);
      tabla.adicionarItem(producto, costo, precioProducto);
      this.tablas.add(tabla);
   }

   public void modificarItem(Producto producto, BigDecimal precioProducto, String uuid) {
      TablaItemPrecio tabla = this.buscarPorUUID(uuid);
      tabla.modificarPrecio(producto, precioProducto);
   }

   public void eliminarItem(Producto producto, String uuid) {
      TablaItemPrecio tabla = this.buscarPorUUID(uuid);
      tabla.eliminarItem(producto);
   }

   private TablaItemPrecio buscarPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemPrecio(uuid));
   }

   public List<ItemPrecio> getItems(String uuid) {
      return this.buscarPorUUID(uuid).getItems();
   }
}
