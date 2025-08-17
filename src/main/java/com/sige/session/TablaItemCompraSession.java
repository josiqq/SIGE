package com.sige.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemCompra;
import com.sige.model.Producto;

@SessionScope
@Component
public class TablaItemCompraSession {
   private Set<TablaItemCompra> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal precio, BigDecimal precioVenta, String uuid) {
      TablaItemCompra tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItem(producto, cantidad, precio, precioVenta);
      this.tablas.add(tabla);
   }

   public void modificarCanidad(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemCompra tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarCanidad(producto, cantidad);
   }

   public void modificarPrecio(Producto producto, BigDecimal precio, String uuid) {
      TablaItemCompra tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarPrecio(producto, precio);
   }

   public void modificarPrecioVenta(Producto producto, BigDecimal precioVenta, String uuid) {
      TablaItemCompra tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarPrecioVenta(producto, precioVenta);
   }

   public void eliminarItem(Producto producto, String uuid) {
      TablaItemCompra tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItem(producto);
   }

   public List<ItemCompra> getItems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   private TablaItemCompra buscarTablaPorUUID(String uuid) {
      return this.tablas.stream().filter(t -> t.getUuid().equals(uuid)).findAny().orElse(new TablaItemCompra(uuid));
   }

   public Object getValorTotal(String uuid) {
      return this.buscarTablaPorUUID(uuid).getSubtotal();
   }
}
