package com.sige.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemApertura;
import com.sige.model.Moneda;

class TablaItemApertura {
   private List<ItemApertura> items = new ArrayList<>();
   private String uuid;

   public TablaItemApertura(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe) {
      Optional<ItemApertura> itemOP = this.recuperarItemExistente(cuenta, condicion, moneda);
      ItemApertura item = new ItemApertura();
      if (itemOP.isPresent()) {
         item = itemOP.get();
         item.setImporte(importe);
      } else {
         item.setCuenta(cuenta);
         item.setCondicion(condicion);
         item.setMoneda(moneda);
         item.setImporte(importe);
         this.items.add(item);
      }
   }

   public void modificarImporte(Cuenta cuenta, Condicion condicion, Moneda moneda, BigDecimal importe) {
      Optional<ItemApertura> itemOP = this.recuperarItemExistente(cuenta, condicion, moneda);
      ItemApertura item = itemOP.get();
      item.setImporte(importe);
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemApertura> getItems() {
      return this.items;
   }

   private Optional<ItemApertura> recuperarItemExistente(Cuenta cuenta, Condicion condicion, Moneda moneda) {
      return this.items.stream().filter(i -> i.getCuenta().equals(cuenta) && i.getCondicion().equals(condicion) && i.getMoneda().equals(moneda)).findAny();
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
         TablaItemApertura other = (TablaItemApertura)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
