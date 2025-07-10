package com.meta.session;

import com.meta.modelo.Cliente;
import com.meta.modelo.ItemClaseGrupal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class TablaItemClaseGrupalSession {
   private Set<TablaItemClaseGrupal> tablas = new HashSet<>();

   public void adicionarItem(Cliente cliente, String uuid) {
      TablaItemClaseGrupal tabla = this.buscarPorUUID(uuid);
      tabla.adicionarItem(cliente);
      this.tablas.add(tabla);
   }

   public void eliminarItem(Cliente cliente, String uuid) {
      TablaItemClaseGrupal tabla = this.buscarPorUUID(uuid);
      tabla.eliminarItem(cliente);
   }

   public List<ItemClaseGrupal> getItems(String uuid) {
      return this.buscarPorUUID(uuid).getItems();
   }

   private TablaItemClaseGrupal buscarPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(uuid)).findAny().orElse(new TablaItemClaseGrupal(uuid));
   }
}
