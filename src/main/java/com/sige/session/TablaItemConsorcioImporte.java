package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemConsorcioImporte;

class TablaItemConsorcioImporte {
   public List<ItemConsorcioImporte> items = new ArrayList<>();
   private String uuid;

   public TablaItemConsorcioImporte(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(LocalDate fecha, BigDecimal monto, BigDecimal montoCobrado, BigDecimal saldo) {
      ItemConsorcioImporte item = new ItemConsorcioImporte();
      item.setFecha(fecha);
      item.setMonto(monto);
      item.setMontoCobrado(montoCobrado);
      item.setSaldo(saldo);
      this.items.add(item);
   }

   public void modificarItem(LocalDate fecha, BigDecimal montoCobrado, BigDecimal saldo) {
      Optional<ItemConsorcioImporte> itemOp = this.getItemByFecha(fecha);
      ItemConsorcioImporte item = itemOp.get();
      item.setMontoCobrado(montoCobrado);
      item.setSaldo(saldo);
   }

   public void eliminarItem(LocalDate fecha) {
      int inidice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getFecha().equals(fecha)).findAny().getAsInt();
      this.items.remove(inidice);
   }

   public List<ItemConsorcioImporte> getItems() {
      return this.items;
   }

   private Optional<ItemConsorcioImporte> getItemByFecha(LocalDate fecha) {
      return this.items.stream().filter(i -> i.getFecha().equals(fecha)).findAny();
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
         TablaItemConsorcioImporte other = (TablaItemConsorcioImporte)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
