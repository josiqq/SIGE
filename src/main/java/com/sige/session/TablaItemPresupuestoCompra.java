package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sige.model.ItemPresupuestoCompra;
import com.sige.model.Producto;

class TablaItemPresupuestoCompra {
   private List<ItemPresupuestoCompra> items = new ArrayList<>();
   private String uuid;

   public TablaItemPresupuestoCompra(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, BigDecimal cantidad, BigDecimal precio) {
      Optional<ItemPresupuestoCompra> itemOP = this.getByProducto(producto);
      ItemPresupuestoCompra item = new ItemPresupuestoCompra();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setCantidad(item.getCantidad().add(cantidad));
      } else {
         item.setProducto(producto);
         item.setCantidad(cantidad);
         item.setPrecio(precio);
         this.items.add(item);
      }
   }

   public void modifiarCantidad(Producto producto, BigDecimal cantidad) {
      ItemPresupuestoCompra item = this.getByProducto(producto).get();
      item.setCantidad(cantidad);
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemPresupuestoCompra> getItems() {
      return this.items;
   }

   public BigDecimal getTotal() {
      return this.items.stream().map(ItemPresupuestoCompra::getSubTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   private Optional<ItemPresupuestoCompra> getByProducto(Producto producto) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto)).findAny();
   }

   public String getUuid() {
      return this.uuid;
   }
}
