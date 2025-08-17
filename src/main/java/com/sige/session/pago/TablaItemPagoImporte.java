package com.sige.session.pago;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPagoImporte;
import com.sige.model.Moneda;

class TablaItemPagoImporte {
   public List<ItemPagoImporte> items = new ArrayList<>();
   private String uuid;

   public TablaItemPagoImporte(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importeMs, Cuenta cuenta, BigDecimal importe) {
      Optional<ItemPagoImporte> itemOp = this.buscarPorCondicionMonedaCuenta(condicion, moneda, cuenta);
      ItemPagoImporte itemPagoImporte = new ItemPagoImporte();
      if (itemOp.isPresent()) {
         itemPagoImporte = itemOp.get();
         itemPagoImporte.setImporte(importe);
         itemPagoImporte.setImporteMs(importeMs);
      } else {
         itemPagoImporte.setCondicion(condicion);
         itemPagoImporte.setMoneda(moneda);
         itemPagoImporte.setImporteMs(importeMs);
         itemPagoImporte.setCuenta(cuenta);
         itemPagoImporte.setImporte(importe);
         this.items.add(itemPagoImporte);
      }
   }

   public void modificarItem(int indice, Cuenta cuenta, BigDecimal importe) {
      Optional<ItemPagoImporte> itemPt = IntStream.range(0, this.items.size()).filter(i -> i == indice).mapToObj(this.items::get).findAny();
      new ItemPagoImporte();
      if (itemPt.isPresent()) {
         ItemPagoImporte itemPagoImporte = itemPt.get();
         itemPagoImporte.setImporte(importe);
         itemPagoImporte.setCuenta(cuenta);
      } else {
         System.out.println("no existe!!" + indice);
      }
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemPagoImporte> getItems() {
      return this.items;
   }

   public BigDecimal getTotalImporte() {
      return this.items.stream().map(i -> i.getImporteMs()).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   private Optional<ItemPagoImporte> buscarPorCondicionMonedaCuenta(Condicion condicion, Moneda moneda, Cuenta cuenta) {
      return this.items.stream().filter(i -> i.getCuenta().equals(cuenta) && i.getCondicion().equals(condicion) && i.getMoneda().equals(moneda)).findAny();
   }

   public String getUuid() {
      return this.uuid;
   }
}
