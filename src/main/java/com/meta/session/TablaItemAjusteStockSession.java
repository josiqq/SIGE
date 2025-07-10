package com.meta.session;

import com.meta.modelo.ItemAjusteStock;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemAjusteStockSession {
   private Set<TablaItemAjusteStock> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, BigDecimal suma, BigDecimal resta, BigDecimal cantidad, String uuid) {
      TablaItemAjusteStock tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItem(producto, suma, resta, cantidad);
      this.tablas.add(tabla);
   }

   public void modificarItem(Producto producto, BigDecimal suma, BigDecimal resta, BigDecimal cantidad, String uuid) {
      TablaItemAjusteStock tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarItem(producto, suma, resta, cantidad);
   }

   public void eliminarItem(Producto producto, String uuid) {
      TablaItemAjusteStock tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItem(producto);
   }

   public List<ItemAjusteStock> getItems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   private TablaItemAjusteStock buscarTablaPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemAjusteStock(uuid));
   }
}
