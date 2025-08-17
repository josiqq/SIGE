package com.sige.session;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.Cliente;
import com.sige.model.Estado;
import com.sige.model.ItemAgenda;

public class TablaItemAgenda {
   private String uuid;
   private List<ItemAgenda> items = new ArrayList<>();

   public TablaItemAgenda(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Long id, LocalTime hora, Cliente cliente, String Observacion, Estado estado) {
      Optional<ItemAgenda> optionalItemAgenda = this.buscarItemPorHora(hora);
      if (!optionalItemAgenda.isPresent()) {
         ItemAgenda itemAgenda = new ItemAgenda();
         itemAgenda.setId(id);
         itemAgenda.setHora(hora);
         itemAgenda.setCliente(cliente);
         itemAgenda.setObservacion(Observacion);
         itemAgenda.setEstado(estado);
         this.items.add(itemAgenda);
      }
   }

   public void modificarItem(LocalTime hora, Cliente cliente, String observacion, Estado estado) {
      ItemAgenda itemAgenda = this.buscarItemPorHora(hora).get();
      itemAgenda.setCliente(cliente);
      itemAgenda.setObservacion(observacion);
      itemAgenda.setEstado(estado);
   }

   public void dejarLibre(LocalTime hora) {
      ItemAgenda itemAgenda = this.buscarItemPorHora(hora).get();
      itemAgenda.setCliente(null);
      itemAgenda.setObservacion(null);
      itemAgenda.setEstado(Estado.LIBRE);
   }

   public List<ItemAgenda> getItems() {
      return this.items;
   }

   public List<ItemAgenda> getHorasLibres(LocalTime hora) {
      return this.items.stream().filter(i -> i.getEstado().equals(Estado.LIBRE)).filter(i -> i.getHora().getHour() >= hora.getHour()).toList();
   }

   private Optional<ItemAgenda> buscarItemPorHora(LocalTime hora) {
      return this.items.stream().filter(i -> i.getHora().equals(hora)).findAny();
   }

   public String getUuid() {
      return this.uuid;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.uuid);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         TablaItemAgenda other = (TablaItemAgenda)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
