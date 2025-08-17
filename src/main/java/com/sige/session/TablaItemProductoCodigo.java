package com.sige.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.ItemProductoCodigo;

class TablaItemProductoCodigo {
   public List<ItemProductoCodigo> items = new ArrayList<>();
   private String uuid;

   public TablaItemProductoCodigo(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(String codigoAlternativo) {
      Optional<ItemProductoCodigo> itemOP = this.items.stream().filter(i -> i.getCodigoAlternativo().equals(codigoAlternativo)).findAny();
      if (itemOP.isEmpty()) {
         ItemProductoCodigo item = new ItemProductoCodigo();
         item.setCodigoAlternativo(codigoAlternativo);
         this.items.add(item);
      }
   }

   public void eliminarItem(int intice) {
      this.items.remove(intice);
   }

   public List<ItemProductoCodigo> getItems() {
      return this.items;
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
         TablaItemProductoCodigo other = (TablaItemProductoCodigo)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
