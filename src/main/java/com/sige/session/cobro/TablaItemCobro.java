package com.sige.session.cobro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.ComisionTarjeta;
import com.sige.model.ItemCobro;
import com.sige.model.Venta;

class TablaItemCobro {
   public List<ItemCobro> itemsCobros = new ArrayList<>();
   private String UUID;

   public TablaItemCobro(String UUID) {
      this.UUID = UUID;
   }

   public void adicionarItem(Venta venta, LocalDate fecha, BigDecimal importe) {
      Optional<ItemCobro> optionalItem = this.buscarItemPorVenta(venta);
      if (optionalItem.isEmpty()) {
         ItemCobro itemCobro = new ItemCobro();
         itemCobro.setVenta(venta);
         itemCobro.setFecha(fecha);
         itemCobro.setImporte(importe);
         this.itemsCobros.add(itemCobro);
      }
   }

   public void adicionarItemComisionTarjeta(ComisionTarjeta comisionTarjeta, LocalDate fecha, BigDecimal importe) {
      ItemCobro itemCobro = new ItemCobro();
      itemCobro.setComisionTarjeta(comisionTarjeta);
      itemCobro.setFecha(fecha);
      itemCobro.setImporte(importe);
      this.itemsCobros.add(itemCobro);
   }

   public void eliminarItem(int indice) {
      this.itemsCobros.remove(indice);
   }

   public void eliminarItemConVenta(Venta venta) {
      int indice = IntStream.range(0, this.itemsCobros.size()).filter(i -> this.itemsCobros.get(i).getVenta().equals(venta)).findAny().getAsInt();
      this.itemsCobros.remove(indice);
   }

   public void modificarItem(Venta venta, BigDecimal importe) {
      ItemCobro itemCobro = this.buscarItemPorVenta(venta).get();
      if (importe.compareTo(itemCobro.getImporte()) < 0) {
         itemCobro.setImporte(importe);
      }
   }

   public List<ItemCobro> getItems() {
      return this.itemsCobros;
   }

   public BigDecimal getTotal() {
      return this.itemsCobros.stream().map(i -> i.getImporte()).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   private Optional<ItemCobro> buscarItemPorVenta(Venta venta) {
      return this.itemsCobros.stream().filter(i -> i.getVenta().equals(venta)).findAny();
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
         TablaItemCobro other = (TablaItemCobro)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
