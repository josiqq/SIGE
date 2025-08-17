package com.sige.session.planilla;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.sige.model.Condicion;
import com.sige.model.ItemPlanilla;
import com.sige.model.Moneda;

class TablaItemPlanilla {
   private String uuid;
   List<ItemPlanilla> itemPlanillas = new ArrayList<>();

   public TablaItemPlanilla(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItem(Condicion condicion, Moneda moneda, BigDecimal importe) {
      Optional<ItemPlanilla> itemOp = this.getByMonedaCondicion(condicion, moneda);
      ItemPlanilla item = new ItemPlanilla();
      if (itemOp.isPresent()) {
         item = itemOp.get();
         item.setImporte(importe);
      } else {
         item.setCondicion(condicion);
         item.setMoneda(moneda);
         item.setImporte(importe);
         this.itemPlanillas.add(item);
      }
   }

   public void eliminarItem(int indice) {
      this.itemPlanillas.remove(indice);
   }

   public List<ItemPlanilla> getItemPlanillas() {
      return this.itemPlanillas;
   }

   private Optional<ItemPlanilla> getByMonedaCondicion(Condicion condicion, Moneda moneda) {
      return this.itemPlanillas.stream().filter(i -> i.getMoneda().equals(moneda) && i.getCondicion().equals(condicion)).findAny();
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
         TablaItemPlanilla other = (TablaItemPlanilla)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
