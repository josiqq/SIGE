package com.sige.session.pago;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.Compra;
import com.sige.model.ItemPago;

@Component
@SessionScope
public class TablaItemPagoSession {
   public Set<TablaItemPago> tablas = new HashSet<>();

   public void adicionarItem(Compra compra, BigDecimal importe, String uuid) {
      TablaItemPago tabla = this.buscarPorUuid(uuid);
      tabla.adicionarItem(compra, importe);
      this.tablas.add(tabla);
   }

   public void modificarItem(Compra compra, BigDecimal cantidad, String uuid) {
      TablaItemPago tablaItemPago = this.buscarPorUuid(uuid);
      tablaItemPago.modificarItem(compra, cantidad);
   }

   public void eliminarItem(Compra compra, String uuid) {
      TablaItemPago tabla = this.buscarPorUuid(uuid);
      tabla.eliminarItem(compra);
   }

   public BigDecimal getTotalImporte(String uuid) {
      return this.buscarPorUuid(uuid).getTotalImporte();
   }

   public List<ItemPago> getItems(String uuid) {
      return this.buscarPorUuid(uuid).getItems();
   }

   private TablaItemPago buscarPorUuid(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemPago(uuid));
   }
}
