package com.sige.session.planilla;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemPlanillaRendicion;
import com.sige.model.Moneda;

class TablaItemPlanillaRendicion {
   private List<ItemPlanillaRendicion> items = new ArrayList<>();
   private String UUID;

   public TablaItemPlanillaRendicion(String uuid) {
      this.UUID = uuid;
   }

   public void adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importe, Cuenta cuentaOrigen, Cuenta CuentaDestino, BigDecimal importemn) {
      Optional<ItemPlanillaRendicion> itemOp = this.items.stream().filter(i -> i.getCondicion().equals(condicion) && i.getMoneda().equals(moneda)).findAny();
      ItemPlanillaRendicion item = new ItemPlanillaRendicion();
      if (itemOp.isPresent()) {
         item = itemOp.get();
         item.setImporte(importe);
         item.setCuentaDestino(CuentaDestino);
      } else {
         item.setCondicion(condicion);
         item.setMoneda(moneda);
         item.setImporte(importe);
         item.setCuenta(cuentaOrigen);
         item.setCuentaDestino(CuentaDestino);
         item.setImporteMn(importemn);
         this.items.add(item);
      }
   }

   public void eliminarItem(int indice) {
      this.items.remove(indice);
   }

   public List<ItemPlanillaRendicion> getItems() {
      return this.items;
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
         TablaItemPlanillaRendicion other = (TablaItemPlanillaRendicion)obj;
         return Objects.equals(this.UUID, other.UUID);
      }
   }
}
