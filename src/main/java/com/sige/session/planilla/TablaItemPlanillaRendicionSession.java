package com.sige.session.planilla;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPlanillaRendicion;
import com.sige.model.Moneda;

@SessionScope
@Component
public class TablaItemPlanillaRendicionSession {
   public Set<TablaItemPlanillaRendicion> tablas = new HashSet<>();

   public void adicionarItem(
      Condicion condicion, Moneda moneda, BigDecimal importe, Cuenta cuentaOrigen, Cuenta CuentaDestino, BigDecimal importemn, String uuid
   ) {
      TablaItemPlanillaRendicion tabla = this.getByUUID(uuid);
      tabla.adicionarItem(condicion, moneda, importe, cuentaOrigen, CuentaDestino, importemn);
      this.tablas.add(tabla);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemPlanillaRendicion tabla = this.getByUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemPlanillaRendicion> getItems(String uuid) {
      return this.getByUUID(uuid).getItems();
   }

   private TablaItemPlanillaRendicion getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemPlanillaRendicion(uuid));
   }
}
