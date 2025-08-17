package com.sige.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.Cliente;
import com.sige.model.ItemConsorcio;

@SessionScope
@Component
public class TablaItemConsorcioSession {
   Set<TablaItemConsorcio> tablas = new HashSet<>();

   public void adicionarItem(Cliente cliente, BigDecimal monto, Integer meses, String uuid) {
      TablaItemConsorcio tabla = this.getTablaByUUID(uuid);
      tabla.adicionarItem(cliente, monto, meses);
      this.tablas.add(tabla);
   }

   public void eliminarItem(Cliente cliente, String uuid) {
      TablaItemConsorcio tabla = this.getTablaByUUID(uuid);
      tabla.eliminarItem(cliente);
   }

   public List<ItemConsorcio> getItems(String uuid) {
      return this.getTablaByUUID(uuid).getItems();
   }

   private TablaItemConsorcio getTablaByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemConsorcio(uuid));
   }
}
