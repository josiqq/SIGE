package com.sige.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.Producto;

@SessionScope
@Component
public class TablaItemPresupuestoCompraSession {
   private Set<TablaItemPresupuestoCompra> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal precio, String uuid) {
      TablaItemPresupuestoCompra tabla = this.getByUUI(uuid);
      tabla.adicionarItem(producto, cantidad, precio);
      this.tablas.add(tabla);
   }

   public void modificarCanidad(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemPresupuestoCompra tabla = this.getByUUI(uuid);
      tabla.modifiarCantidad(producto, cantidad);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemPresupuestoCompra tabla = this.getByUUI(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemPresupuestoCompra> getItems(String uuid) {
      return this.getByUUI(uuid).getItems();
   }

   public BigDecimal getTotal(String uuid) {
      return this.getByUUI(uuid).getTotal();
   }

   private TablaItemPresupuestoCompra getByUUI(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemPresupuestoCompra(uuid));
   }
}
