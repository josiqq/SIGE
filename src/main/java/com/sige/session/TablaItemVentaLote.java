package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sige.model.ItemVentaLote;
import com.sige.model.Producto;

class TablaItemVentaLote {
   private List<ItemVentaLote> items = new ArrayList<>();
   private String uuid;

   public TablaItemVentaLote(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, String nroLote, LocalDate vencimiento, BigDecimal cantidad, BigDecimal cantidadActual) {
      Optional<ItemVentaLote> itemOP = this.getByProductoLote(producto, nroLote);
      ItemVentaLote item = new ItemVentaLote();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setCantidad(item.getCantidad().add(cantidad));
         item.setCantidadActual(cantidadActual);
      } else {
         item.setProducto(producto);
         item.setNroLote(nroLote);
         item.setVencimiento(vencimiento);
         item.setCantidad(cantidad);
         item.setCantidadActual(cantidadActual);
         this.items.add(item);
      }
   }

   public void modificarCantidad(Producto producto, String nroLote, BigDecimal cantidad, BigDecimal cantidadActual) {
      Optional<ItemVentaLote> itemOP = this.getByProductoLote(producto, nroLote);
      ItemVentaLote item = itemOP.get();
      item.setCantidad(cantidad);
      item.setCantidadActual(cantidadActual);
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemVentaLote> getItems() {
      return this.items;
   }

   public BigDecimal getCantidadTotal(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).map(ItemVentaLote::getCantidad).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   public List<ItemVentaLote> getItemsByProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).collect(Collectors.toList());
   }

   private Optional<ItemVentaLote> getByProductoLote(Producto producto, String nroLote) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto) && i.getNroLote().equals(nroLote)).findAny();
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
         TablaItemVentaLote other = (TablaItemVentaLote)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
