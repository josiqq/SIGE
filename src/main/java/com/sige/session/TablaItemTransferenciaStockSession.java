package com.sige.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemTransferenciaStock;
import com.sige.model.Producto;

@SessionScope
@Component
public class TablaItemTransferenciaStockSession {
   public Set<TablaItemTransferenciaStock> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemTransferenciaStock tabla = this.getByUUID(uuid);
      tabla.adicionarItem(producto, cantidad);
      this.tablas.add(tabla);
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad, String uuid) {
      TablaItemTransferenciaStock tabla = this.getByUUID(uuid);
      tabla.modificarCantidad(producto, cantidad);
   }

   public void eliminarItem(int indice, String uuid) {
      this.getByUUID(uuid).eliminarItem(indice);
   }

   public List<ItemTransferenciaStock> getItems(String uuid) {
      return this.getByUUID(uuid).getItems();
   }

   private TablaItemTransferenciaStock getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemTransferenciaStock(uuid));
   }
}
