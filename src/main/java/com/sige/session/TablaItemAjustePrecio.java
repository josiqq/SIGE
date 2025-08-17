package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemAjustePrecio;
import com.sige.model.Producto;

class TablaItemAjustePrecio {
   private String uuid;
   public List<ItemAjustePrecio> items = new ArrayList<>();

   public TablaItemAjustePrecio(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, BigDecimal costo, BigDecimal precioProducto, BigDecimal precioMinimo) {
      Optional<ItemAjustePrecio> optionalItemAjustePrecio = this.buscarItemPorProducto(producto);
      if (!optionalItemAjustePrecio.isPresent()) {
         ItemAjustePrecio itemAjustePrecio = new ItemAjustePrecio();
         itemAjustePrecio.setProducto(producto);
         itemAjustePrecio.setCosto(costo);
         itemAjustePrecio.setPrecioProducto(precioProducto);
         itemAjustePrecio.setPrecioMinimo(precioMinimo);
         itemAjustePrecio.setUtilidad(precioProducto.subtract(costo));
         this.items.add(0, itemAjustePrecio);
      }
   }

   public void modificarItemPrecioMinimo(Producto producto, BigDecimal precioMinimo) {
      ItemAjustePrecio itemAjustePrecio = this.buscarItemPorProducto(producto).get();
      itemAjustePrecio.setPrecioMinimo(precioMinimo);
   }

   public void modificarItemPrecioProducto(Producto producto, BigDecimal precioProducto) {
      ItemAjustePrecio itemAjustePrecio = this.buscarItemPorProducto(producto).get();
      itemAjustePrecio.setPrecioProducto(precioProducto);
      itemAjustePrecio.setUtilidad(precioProducto.subtract(itemAjustePrecio.getCosto()));
   }

   public void eliminarItem(Producto producto) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getProducto().equals(producto)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public List<ItemAjustePrecio> getItems() {
      return this.items;
   }

   private Optional<ItemAjustePrecio> buscarItemPorProducto(Producto producto) {
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
         TablaItemAjustePrecio other = (TablaItemAjustePrecio)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
