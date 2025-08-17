package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sige.model.ItemPresupuestoVenta;
import com.sige.model.Producto;

class TablaItemPresupuestoVenta {
   private List<ItemPresupuestoVenta> items = new ArrayList<>();
   private String uuid;

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal costo, BigDecimal precio) {
      Optional<ItemPresupuestoVenta> itemOP = this.getByProducto(producto);
      ItemPresupuestoVenta item = new ItemPresupuestoVenta();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setCantidad(item.getCantidad().add(cantidad));
      } else {
         item.setProducto(producto);
         item.setCantidad(cantidad);
         item.setCosto(costo);
         item.setPrecio(precio);
         this.items.add(item);
      }
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad) {
      Optional<ItemPresupuestoVenta> itemOP = this.getByProducto(producto);
      new ItemPresupuestoVenta();
      if (itemOP.isPresent()) {
         ItemPresupuestoVenta item = itemOP.get();
         item.setCantidad(cantidad);
      }
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemPresupuestoVenta> getItems() {
      return this.items;
   }

   public void modificarPrecioCosto(Producto producto, BigDecimal precio, BigDecimal costo) {
      Optional<ItemPresupuestoVenta> itemOP = this.getByProducto(producto);
      ItemPresupuestoVenta item = itemOP.get();
      item.setPrecio(precio);
      item.setCosto(costo);
   }

   public BigDecimal getTotal() {
      return this.items.stream().map(ItemPresupuestoVenta::subTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   private Optional<ItemPresupuestoVenta> getByProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).findAny();
   }

   public TablaItemPresupuestoVenta(String uuid) {
      this.uuid = uuid;
   }

   public String getUuid() {
      return this.uuid;
   }
}
