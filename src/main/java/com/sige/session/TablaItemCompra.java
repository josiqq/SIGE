package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemCompra;
import com.sige.model.Producto;

class TablaItemCompra {
   private String uuid;
   private List<ItemCompra> items = new ArrayList<>();

   public TablaItemCompra(String uuid) {
      this.uuid = uuid;
   }

   public BigDecimal getSubtotal() {
      return this.items.stream().map(ItemCompra::getSubtotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
   }

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal precio, BigDecimal precioVenta) {
      Optional<ItemCompra> OptionaItemCompra = this.buscarItemProducto(producto);
      ItemCompra itemCompra = null;
      if (OptionaItemCompra.isPresent()) {
         itemCompra = OptionaItemCompra.get();
         itemCompra.setCantidad(itemCompra.getCantidad().add(cantidad));
      } else {
         itemCompra = new ItemCompra();
         itemCompra.setProducto(producto);
         itemCompra.setCantidad(cantidad);
         itemCompra.setPrecio(precio);
         itemCompra.setPrecioVenta(precioVenta);
         this.items.add(0, itemCompra);
      }
   }

   public void modificarCanidad(Producto producto, BigDecimal cantidad) {
      ItemCompra itemCompra = this.buscarItemProducto(producto).get();
      itemCompra.setCantidad(cantidad);
   }

   public void modificarPrecio(Producto producto, BigDecimal precio) {
      ItemCompra itemCompra = this.buscarItemProducto(producto).get();
      itemCompra.setPrecio(precio);
   }

   public void modificarPrecioVenta(Producto producto, BigDecimal precioVenta) {
      ItemCompra itemCompra = this.buscarItemProducto(producto).get();
      itemCompra.setPrecioVenta(precioVenta);
   }

   public void eliminarItem(Producto producto) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getProducto().equals(producto)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public int total() {
      return this.items.size();
   }

   public List<ItemCompra> getItems() {
      return this.items;
   }

   private Optional<ItemCompra> buscarItemProducto(Producto producto) {
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
         TablaItemCompra other = (TablaItemCompra)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
