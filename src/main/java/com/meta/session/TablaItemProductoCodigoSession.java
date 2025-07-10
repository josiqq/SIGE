package com.meta.session;

import com.meta.modelo.ItemProductoCodigo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemProductoCodigoSession {
   public Set<TablaItemProductoCodigo> tablas = new HashSet<>();

   public void adicionarItem(String codigoAlternativo, String uuid) {
      TablaItemProductoCodigo tabla = this.getByUUID(uuid);
      tabla.adicionarItem(codigoAlternativo);
      this.tablas.add(tabla);
   }

   public void eliminarItem(int indice, String uuid) {
      TablaItemProductoCodigo tabla = this.getByUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public List<ItemProductoCodigo> getItems(String uuid) {
      return this.getByUUID(uuid).getItems();
   }

   private TablaItemProductoCodigo getByUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemProductoCodigo(uuid));
   }
}
