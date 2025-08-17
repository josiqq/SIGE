package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.Cliente;
import com.sige.model.ItemConsorcio;

class TablaItemConsorcio {
   public List<ItemConsorcio> items = new ArrayList<>();
   private String UUID;

   public TablaItemConsorcio(String uuid) {
      this.UUID = uuid;
   }

   public void adicionarItem(Cliente cliente, BigDecimal monto, Integer meses) {
      Optional<ItemConsorcio> itemOp = this.getByCliente(cliente);
      if (itemOp.isEmpty()) {
         ItemConsorcio itemConsorcio = new ItemConsorcio();
         itemConsorcio.setCliente(cliente);
         itemConsorcio.setMonto(monto);
         itemConsorcio.setMeses(meses);
         this.items.add(0, itemConsorcio);
      }
   }

   public void eliminarItem(Cliente cliente) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getCliente().equals(cliente)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public List<ItemConsorcio> getItems() {
      return this.items;
   }

   private Optional<ItemConsorcio> getByCliente(Cliente cliente) {
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
         TablaItemConsorcio other = (TablaItemConsorcio)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
