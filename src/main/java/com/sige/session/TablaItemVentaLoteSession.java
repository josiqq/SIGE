package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemVentaLote;
import com.sige.model.Producto;

@Component
@SessionScope
public class TablaItemVentaLoteSession {
   public Set<TablaItemVentaLote> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, String nroLote, LocalDate vencimiento, BigDecimal cantidad, BigDecimal cantidadActual, String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      tabla.adicionarItem(producto, nroLote, vencimiento, cantidad, cantidadActual);
      this.tablas.add(tabla);
   }

   public void modificarCantidad(Producto producto, String nroLote, BigDecimal cantidad, BigDecimal cantidadActual, String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      tabla.modificarCantidad(producto, nroLote, cantidad, cantidadActual);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemVentaLote> getItems(String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      return tabla.getItems();
   }

   public BigDecimal getCantidadTotal(Producto producto, String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      return tabla.getCantidadTotal(producto);
   }

   public List<ItemVentaLote> getItemsByProducto(Producto producto, String uuid) {
      TablaItemVentaLote tabla = this.getByUUID(uuid);
      return tabla.getItemsByProducto(producto);
   }

   private TablaItemVentaLote getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemVentaLote(uuid));
   }
}
