package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemAjusteLote;
import com.sige.model.Producto;

@Component
@SessionScope
public class TablaItemAjusteLoteSession {
   public Set<TablaItemAjusteLote> tablas = new HashSet<>();

   public void adicionarItem(Producto producto, String nroLote, BigDecimal cantidad, LocalDate vencimiento, String uuid) {
      TablaItemAjusteLote tabla = this.getUUID(uuid);
      tabla.adicionarItem(producto, nroLote, cantidad, vencimiento);
      this.tablas.add(tabla);
   }

   public void modificarCantidad(Producto producto, String nroLote, BigDecimal cantidad, String uuid) {
      TablaItemAjusteLote tabla = this.getUUID(uuid);
      tabla.modificarCantidad(producto, nroLote, cantidad);
   }

   public void modificarVencimiento(Producto producto, String nroLote, LocalDate vencimiento, String uuid) {
      TablaItemAjusteLote tabla = this.getUUID(uuid);
      tabla.modificarVencimiento(producto, nroLote, vencimiento);
   }

   public void eliminarItem(int indice, String uuid) {
      this.getUUID(uuid).eliminarItem(indice);
   }

   public List<ItemAjusteLote> getItems(String uuid) {
      return this.getUUID(uuid).getItems();
   }

   private TablaItemAjusteLote getUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemAjusteLote(uuid));
   }
}
