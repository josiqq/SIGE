package com.sige.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.Cliente;
import com.sige.model.ItemClaseGrupal;

class TablaItemClaseGrupal {
   private List<ItemClaseGrupal> items = new ArrayList<>();
   private String UUID;

   public TablaItemClaseGrupal(String UUID) {
      this.UUID = UUID;
   }

   public List<ItemClaseGrupal> getItems() {
      return this.items;
   }

   public void adicionarItem(Cliente cliente) {
      Optional<ItemClaseGrupal> claseOp = this.buscarItemPorCliente(cliente);
      if (claseOp.isEmpty()) {
         ItemClaseGrupal clase = new ItemClaseGrupal();
         clase.setCliente(cliente);
         this.items.add(0, clase);
      }
   }

   public void eliminarItem(Cliente cliente) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getCliente().equals(cliente)).findAny().getAsInt();
      this.items.remove(indice);
   }

   private Optional<ItemClaseGrupal> buscarItemPorCliente(Cliente cliente) {
      return this.items.stream().filter(i -> i.getCliente().equals(cliente)).findAny();
   }

   public String getUUID() {
      return this.UUID;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.UUID);
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
         TablaItemClaseGrupal other = (TablaItemClaseGrupal)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
