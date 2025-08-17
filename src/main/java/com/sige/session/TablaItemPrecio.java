package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemPrecio;
import com.sige.model.Producto;

class TablaItemPrecio {
   private String uuid;
   private List<ItemPrecio> items = new ArrayList<>();

   public TablaItemPrecio(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, BigDecimal costo, BigDecimal precioProducto) {
      Optional<ItemPrecio> optionalItemPrecio = this.buscarItemPorProducto(producto);
      ItemPrecio itemPrecio = null;
      if (!optionalItemPrecio.isPresent()) {
         itemPrecio = new ItemPrecio();
         itemPrecio.setProducto(producto);
         itemPrecio.setCosto(costo);
         itemPrecio.setPrecioProducto(precioProducto);
         this.items.add(0, itemPrecio);
      } else {
         itemPrecio = optionalItemPrecio.get();
         itemPrecio.setCosto(costo);
         itemPrecio.setPrecioProducto(precioProducto);
      }
   }

   public void modificarPrecio(Producto producto, BigDecimal precioProducto) {
      ItemPrecio itemPrecio = this.buscarItemPorProducto(producto).get();
      itemPrecio.setPrecioProducto(precioProducto);
   }

   public void eliminarItem(Producto producto) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getProducto().equals(producto)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public List<ItemPrecio> getItems() {
      return this.items;
   }

   private Optional<ItemPrecio> buscarItemPorProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).findAny();
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
         TablaItemPrecio other = (TablaItemPrecio)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
