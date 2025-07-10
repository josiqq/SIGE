package com.meta.session;

import com.meta.modelo.ItemPresupuestoVenta;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class TablaItemPresupuestoVentaSession {
   public Set<TablaItemPresupuestoVenta> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal costo, BigDecimal precio, String uuid) {
      TablaItemPresupuestoVenta tabla = this.getByUUID(uuid);
      tabla.adicionarItem(producto, cantidad, costo, precio);
      this.tablas.add(tabla);
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemPresupuestoVenta tabla = this.getByUUID(uuid);
      tabla.modificarCantidad(producto, cantidad);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemPresupuestoVenta tabla = this.getByUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemPresupuestoVenta> getItems(String uuid) {
      return this.getByUUID(uuid).getItems();
   }

   public void modificarPrecioCosto(Producto producto, BigDecimal precio, BigDecimal costo, String uuid) {
      TablaItemPresupuestoVenta tabla = this.getByUUID(uuid);
      tabla.modificarPrecioCosto(producto, precio, costo);
   }

   public BigDecimal getTotal(String uuid) {
      return this.getByUUID(uuid).getTotal();
   }

   private TablaItemPresupuestoVenta getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemPresupuestoVenta(uuid));
   }
}
