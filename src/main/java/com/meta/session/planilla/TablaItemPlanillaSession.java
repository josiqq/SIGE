package com.meta.session.planilla;

import com.meta.modelo.Condicion;
import com.meta.modelo.ItemPlanilla;
import com.meta.modelo.Moneda;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemPlanillaSession {
   public Set<TablaItemPlanilla> tablas = new HashSet<>();

   public void adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importem, String uuid) {
      TablaItemPlanilla tabla = this.getByUuid(uuid);
      tabla.adicionarItem(condicion, moneda, importem);
      this.tablas.add(tabla);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemPlanilla tabla = this.getByUuid(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemPlanilla> getItemPlanillas(String uuid) {
      return this.getByUuid(uuid).getItemPlanillas();
   }

   private TablaItemPlanilla getByUuid(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemPlanilla(uuid));
   }
}
