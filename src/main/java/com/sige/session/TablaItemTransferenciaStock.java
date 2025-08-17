package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.ItemTransferenciaStock;
import com.sige.model.Producto;

class TablaItemTransferenciaStock {
   private List<ItemTransferenciaStock> items = new ArrayList<>();
   private String uuid;

   public TablaItemTransferenciaStock(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, BigDecimal cantidad) {
      Optional<ItemTransferenciaStock> itemOP = this.getByProducto(producto);
      ItemTransferenciaStock item = new ItemTransferenciaStock();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setCantidad(item.getCantidad().add(cantidad));
      } else if (producto.getId() != null) {
         item.setCantidad(cantidad);
         item.setProducto(producto);
         this.items.add(item);
      }
   }

   public void modificarCantidad(Producto producto, BigDecimal cantidad) {
      Optional<ItemTransferenciaStock> itemOP = this.getByProducto(producto);
      ItemTransferenciaStock item = itemOP.get();
      item.setCantidad(cantidad);
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemTransferenciaStock> getItems() {
      return this.items;
   }

   private Optional<ItemTransferenciaStock> getByProducto(Producto producto) {
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
         TablaItemTransferenciaStock other = (TablaItemTransferenciaStock)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
