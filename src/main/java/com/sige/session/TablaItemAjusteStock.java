package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemAjusteStock;
import com.sige.model.Producto;

class TablaItemAjusteStock {
   private List<ItemAjusteStock> items = new ArrayList<>();
   private String UUID;

   public TablaItemAjusteStock(String UUID) {
      this.UUID = UUID;
   }

   public void adicionarItem(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad) {
      Optional<ItemAjusteStock> itemOp = this.buscarItemPorProducto(producto);
      ItemAjusteStock itemAjusteStock = new ItemAjusteStock();
      if (itemOp.isPresent()) {
         itemAjusteStock = itemOp.get();
         itemAjusteStock.setSumar(sumar);
         itemAjusteStock.setRestar(restar);
         itemAjusteStock.setCantidad(cantidad);
      } else {
         itemAjusteStock.setProducto(producto);
         itemAjusteStock.setSumar(sumar);
         itemAjusteStock.setRestar(restar);
         itemAjusteStock.setCantidad(cantidad);
         this.items.add(0, itemAjusteStock);
      }
   }

   public void modificarItem(Producto producto, BigDecimal sumar, BigDecimal restar, BigDecimal cantidad) {
      Optional<ItemAjusteStock> itemOp = this.buscarItemPorProducto(producto);
      ItemAjusteStock itemAjusteStock = itemOp.get();
      itemAjusteStock.setSumar(sumar);
      itemAjusteStock.setRestar(restar);
      itemAjusteStock.setCantidad(cantidad);
   }

   public void eliminarItem(Producto producto) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getProducto().equals(producto)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public List<ItemAjusteStock> getItems() {
      return this.items;
   }

   private Optional<ItemAjusteStock> buscarItemPorProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).findAny();
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
         TablaItemAjusteStock other = (TablaItemAjusteStock)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
