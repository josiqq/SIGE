package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ItemVenta;
import com.sige.model.Producto;

class TablaItemVenta {
   private String UUID;
   public List<ItemVenta> items = new ArrayList<>();

   public TablaItemVenta(String UUID) {
      this.UUID = UUID;
   }

   public BigDecimal getSubTotal() {
      return this.items.stream().map(ItemVenta::getSubTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
   }

   public void adicionarItem(Producto producto, BigDecimal costo, BigDecimal precio, BigDecimal cantidad) {
      Optional<ItemVenta> optionalItemVenta = this.buscarPorProducto(producto);
      ItemVenta itemVenta = null;
      if (optionalItemVenta.isPresent()) {
         itemVenta = optionalItemVenta.get();
         itemVenta.setCantidad(itemVenta.getCantidad().add(cantidad));
      } else {
         itemVenta = new ItemVenta();
         itemVenta.setProducto(producto);
         itemVenta.setCosto(costo);
         itemVenta.setPrecio(precio);
         itemVenta.setCantidad(cantidad);
         this.items.add(0, itemVenta);
      }
   }

   public void modificarPrecio(Producto producto, BigDecimal precio) {
      ItemVenta itemVenta = this.buscarPorProducto(producto).get();
      itemVenta.setPrecio(precio);
   }

   public void modificarPrecioCosto(Producto producto, BigDecimal precio, BigDecimal costo) {
      ItemVenta itemVenta = this.buscarPorProducto(producto).get();
      itemVenta.setPrecio(precio);
      itemVenta.setCosto(costo);
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad) {
      ItemVenta itemVenta = this.buscarPorProducto(producto).get();
      itemVenta.setCantidad(cantidad);
   }

   public void eliminarItem(Producto producto) {
      int indice = IntStream.range(0, this.items.size()).filter(i -> this.items.get(i).getProducto().equals(producto)).findAny().getAsInt();
      this.items.remove(indice);
   }

   public void sumarCantidad(Producto producto) {
      ItemVenta itemVenta = this.buscarPorProducto(producto).get();
      BigDecimal cantidad = itemVenta.getCantidad().add(BigDecimal.ONE);
      itemVenta.setCantidad(cantidad);
   }

   public void restarCantidad(Producto producto) {
      ItemVenta itemVenta = this.buscarPorProducto(producto).get();
      BigDecimal cantidad = itemVenta.getCantidad().subtract(BigDecimal.ONE);
      if (cantidad.compareTo(BigDecimal.ZERO) < 0) {
         cantidad = BigDecimal.ONE;
      }

      if (cantidad.compareTo(BigDecimal.ZERO) == 0) {
         cantidad = BigDecimal.ONE;
      }

      itemVenta.setCantidad(cantidad);
   }

   public List<ItemVenta> getItems() {
      return this.items;
   }

   private Optional<ItemVenta> buscarPorProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).findAny();
   }

   public void eliminarItemIndice(int indice) {
      this.items.remove(indice);
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
         TablaItemVenta other = (TablaItemVenta)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
