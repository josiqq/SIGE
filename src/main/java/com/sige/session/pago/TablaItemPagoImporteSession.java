package com.sige.session.pago;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPagoImporte;
import com.sige.model.Moneda;

@Component
@SessionScope
public class TablaItemPagoImporteSession {
   public Set<TablaItemPagoImporte> tablas = new HashSet<>();

   public void adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importeMs, Cuenta cuenta, BigDecimal importe, String uuid) {
      TablaItemPagoImporte tabla = this.buscarPorUUID(uuid);
      tabla.adicionarItem(condicion, moneda, importeMs, cuenta, importe);
      this.tablas.add(tabla);
   }

   public void modificarItem(int indice, Cuenta cuenta, BigDecimal importe, String uuid) {
      TablaItemPagoImporte tabla = this.buscarPorUUID(uuid);
      tabla.modificarItem(indice, cuenta, importe);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemPagoImporte tabla = this.buscarPorUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemPagoImporte> getItems(String uuid) {
      return this.buscarPorUUID(uuid).getItems();
   }

   public BigDecimal getTotalImporte(String uuid) {
      return this.buscarPorUUID(uuid).getTotalImporte();
   }

   private TablaItemPagoImporte buscarPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemPagoImporte(uuid));
   }
}
