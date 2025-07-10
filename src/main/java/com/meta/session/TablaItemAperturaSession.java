package com.meta.session;

import com.meta.modelo.Condicion;
import com.meta.modelo.Cuenta;
import com.meta.modelo.ItemApertura;
import com.meta.modelo.Moneda;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class TablaItemAperturaSession {
   public Set<TablaItemApertura> tablas = new HashSet<>();

   public void adicionarItem(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe, String uuid) {
      TablaItemApertura tabla = this.getByUUID(uuid);
      tabla.adicionarItem(cuenta, condicion, moneda, importe);
      this.tablas.add(tabla);
   }

   public void modificarImporte(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe, String uuid) {
      TablaItemApertura tabla = this.getByUUID(uuid);
      tabla.modificarImporte(cuenta, condicion, moneda, importe);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemApertura tabla = this.getByUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemApertura> getItems(String uuid) {
      return this.getByUUID(uuid).getItems();
   }

   private TablaItemApertura getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemApertura(uuid));
   }
}
