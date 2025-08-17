package com.sige.session;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sige.model.ItemAjusteLote;
import com.sige.model.Producto;

class TablaItemAjusteLote {
   private List<ItemAjusteLote> items = new ArrayList<>();
   private String uuid;

   public TablaItemAjusteLote(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Producto producto, String nroLote, BigDecimal cantidad, LocalDate vencimeinto) {
      Optional<ItemAjusteLote> itemOP = this.getByProductoAndLote(producto, nroLote);
      ItemAjusteLote item = new ItemAjusteLote();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setCantidad(item.getCantidad().add(cantidad));
         item.setVencimiento(vencimeinto);
      } else {
         item.setProducto(producto);
         item.setNroLote(nroLote);
         item.setCantidad(cantidad);
         item.setVencimiento(vencimeinto);
         this.items.add(item);
      }
   }

   public void modificarCantidad(Producto producto, String nroLote, BigDecimal cantidad) {
      Optional<ItemAjusteLote> itemOP = this.getByProductoAndLote(producto, nroLote);
      ItemAjusteLote item = itemOP.get();
      item.setCantidad(cantidad);
   }

   public void modificarVencimiento(Producto producto, String nroLote, LocalDate vencimiento) {
      Optional<ItemAjusteLote> itemOP = this.getByProductoAndLote(producto, nroLote);
      ItemAjusteLote item = itemOP.get();
      item.setVencimiento(vencimiento);
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemAjusteLote> getItems() {
      return this.items;
   }

   private Optional<ItemAjusteLote> getByProductoAndLote(Producto producto, String nroLote) {
      return this.items.stream().filter(i -> i.getProducto().equals(producto) && i.getNroLote().equals(nroLote)).findAny();
   }

   public String getUuid() {
      return this.uuid;
   }
}
