package com.meta.session;

import com.meta.modelo.Cliente;
import com.meta.modelo.Estado;
import com.meta.modelo.ItemAgenda;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class TablaItemAgendaSession {
   Set<TablaItemAgenda> tablas = new HashSet<>();

   public void adicionarItem(Long id, LocalTime hora, Cliente cliente, String observacion, Estado estado, String uuid) {
      TablaItemAgenda tablaItemAgenda = this.buscarTablaPorUUID(uuid);
      tablaItemAgenda.adicionarItem(id, hora, cliente, observacion, estado);
      this.tablas.add(tablaItemAgenda);
   }

   public void modificarItem(LocalTime hora, Cliente cliente, String observacion, Estado estado, String uuid) {
      TablaItemAgenda tablaItemAgenda = this.buscarTablaPorUUID(uuid);
      tablaItemAgenda.modificarItem(hora, cliente, observacion, estado);
   }

   public void dejarLibre(LocalTime hora, String uuid) {
      TablaItemAgenda tablaItemAgenda = this.buscarTablaPorUUID(uuid);
      tablaItemAgenda.dejarLibre(hora);
   }

   public List<ItemAgenda> getIems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   public List<ItemAgenda> getHorasLibres(LocalTime hora, String uuid) {
      return this.buscarTablaPorUUID(uuid).getHorasLibres(hora);
   }

   private TablaItemAgenda buscarTablaPorUUID(String uuid) {
      return this.tablas.stream().filter(t -> t.getUuid().equals(uuid)).findAny().orElse(new TablaItemAgenda(uuid));
   }
}
