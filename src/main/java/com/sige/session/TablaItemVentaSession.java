package com.sige.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemVenta;
import com.sige.model.Producto;

@SessionScope
@Component
public class TablaItemVentaSession {
   public Set<TablaItemVenta> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal precio, BigDecimal costo, BigDecimal cantidad, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItem(producto, costo, precio, cantidad);
      this.tablas.add(tabla);
   }

   public void modificarPrecio(Producto producto, BigDecimal precio, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarPrecio(producto, precio);
   }

   public void modificarPrecioCosto(Producto producto, BigDecimal precio, BigDecimal costo, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarPrecioCosto(producto, precio, costo);
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarCantidad(producto, cantidad);
   }

   public void eliminarItem(Producto producto, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItem(producto);
   }

   public BigDecimal totalPrecio(String uuid) {
      return this.buscarTablaPorUUID(uuid).getSubTotal();
   }

   public List<ItemVenta> getItems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   public void sumarCantidad(Producto producto, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.sumarCantidad(producto);
   }

   public void restarCantidad(Producto producto, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.restarCantidad(producto);
   }

   private TablaItemVenta buscarTablaPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemVenta(uuid));
   }

   public void eliminarItemIndice(int indice, String uuid) {
      TablaItemVenta tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItemIndice(indice);
   }
}
