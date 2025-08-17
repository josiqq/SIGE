package com.sige.session.pago;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.Compra;
import com.sige.model.ItemPago;

class TablaItemPago {
   private List<ItemPago> items = new ArrayList<>();
   private String UUID;

   public TablaItemPago(String UUID) {
      this.UUID = UUID;
   }

   public void adicionarItem(Compra compra, BigDecimal importe) {
      Optional<ItemPago> itemOp = this.buscarItemPorCompra(compra);
      ItemPago itemPago = new ItemPago();
      if (itemOp.isEmpty()) {
         itemPago.setCompra(compra);
         itemPago.setImporte(importe);
         this.items.add(itemPago);
      }
   }

   public void modificarItem(Compra compra, BigDecimal importe) {
      ItemPago itemPago = this.buscarItemPorCompra(compra).get();
      if (itemPago.getImporte().compareTo(importe) > 0) {
         itemPago.setImporte(importe);
      }
   }

   public void eliminarItem(Compra compra) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getCompra().equals(compra)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public BigDecimal getTotalImporte() {
      return this.items.stream().map(i -> i.getImporte()).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   public List<ItemPago> getItems() {
      return this.items;
   }

   private Optional<ItemPago> buscarItemPorCompra(Compra compra) {
      return this.items.stream().filter(i -> i.getCompra().equals(compra)).findAny();
   }

   public String getUUID() {
      return this.UUID;
   }
}
