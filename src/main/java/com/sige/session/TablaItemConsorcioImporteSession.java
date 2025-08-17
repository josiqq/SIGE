package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ItemConsorcioImporte;

@Component
@SessionScope
public class TablaItemConsorcioImporteSession {
   Set<TablaItemConsorcioImporte> tablas = new HashSet<>();

   public void adicionarItem(LocalDate fecha, BigDecimal monto, BigDecimal montoCobrado, BigDecimal saldo, String uuid) {
      TablaItemConsorcioImporte tabla = this.getByUuid(uuid);
      tabla.adicionarItem(fecha, monto, montoCobrado, saldo);
      this.tablas.add(tabla);
   }

   public void modificarItem(LocalDate fecha, BigDecimal montoCobrado, BigDecimal saldo, String uuid) {
      TablaItemConsorcioImporte tabla = this.getByUuid(uuid);
      tabla.modificarItem(fecha, montoCobrado, saldo);
   }

   public void eliminarItem(LocalDate fecha, String uuid) {
      TablaItemConsorcioImporte tabla = this.getByUuid(uuid);
      tabla.eliminarItem(fecha);
   }

   public List<ItemConsorcioImporte> getItems(String uuid) {
      return this.getByUuid(uuid).getItems();
   }

   private TablaItemConsorcioImporte getByUuid(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemConsorcioImporte(uuid));
   }
}
