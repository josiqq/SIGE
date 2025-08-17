package com.sige.session.cobro;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.groovy.parser.antlr4.util.StringUtils;

import com.sige.model.Banco;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemCobroImporte;
import com.sige.model.Moneda;

class TablaItemCobroImporte {
   private List<ItemCobroImporte> items = new ArrayList<>();
   private String uuid;

   public TablaItemCobroImporte(String uuid) {
      this.uuid = uuid;
   }

   public void adicionarItemVentaMB(
      Cuenta cuenta, BigDecimal importe, BigDecimal importeCobrado, BigDecimal vuelto, Condicion condicion, Moneda moneda, Moneda monedaVuelto
   ) {
      Optional<ItemCobroImporte> itemOp = this.items.stream().filter(i -> i.getCondicion().equals(condicion) && i.getMoneda().equals(moneda)).findAny();
      ItemCobroImporte itemCobroImporte = new ItemCobroImporte();
      if (itemOp.isPresent()) {
         System.out.println("ya esxiste esta cuenta !!");
         itemCobroImporte = itemOp.get();
         itemCobroImporte.setImporte(importe);
         itemCobroImporte.setImporteCobrado(importeCobrado);
         itemCobroImporte.setMonedaVuelto(monedaVuelto);
         itemCobroImporte.setVuelto(vuelto);
      } else {
         itemCobroImporte.setCuenta(cuenta);
         itemCobroImporte.setImporte(importe);
         itemCobroImporte.setImporteCobrado(importeCobrado);
         itemCobroImporte.setVuelto(vuelto);
         itemCobroImporte.setMoneda(moneda);
         itemCobroImporte.setMonedaVuelto(monedaVuelto);
         itemCobroImporte.setCondicion(condicion);
         this.items.add(itemCobroImporte);
      }
   }

   public void adicionarItemPrincipal(
      Cuenta cuenta,
      Condicion condicion,
      Moneda moneda,
      BigDecimal valoCotizacion,
      BigDecimal importe,
      BigDecimal importems,
      BigDecimal cobrado,
      Moneda monedaVuelto,
      BigDecimal cotizacionVuelto,
      BigDecimal vuelto,
      BigDecimal vueltoms,
      Banco banco,
      String boleta
   ) {
      Optional<ItemCobroImporte> itemOp = this.items.stream().filter(i -> i.getCondicion().equals(condicion) && i.getMoneda().equals(moneda)).findAny();
      ItemCobroImporte itemCobroImporte = new ItemCobroImporte();
      if (itemOp.isPresent()) {
         System.out.println("ya esxiste esta cuenta !!");
         itemCobroImporte = itemOp.get();
         itemCobroImporte.setImporte(importe);
         itemCobroImporte.setImportems(importems);
         itemCobroImporte.setImporteCobrado(cobrado);
         itemCobroImporte.setVuelto(vuelto);
         itemCobroImporte.setVueltoms(vueltoms);
      } else {
         if (!StringUtils.isEmpty(banco.getNombre())) {
            itemCobroImporte.setBanco(banco);
         }

         itemCobroImporte.setCuenta(cuenta);
         itemCobroImporte.setCondicion(condicion);
         itemCobroImporte.setMoneda(moneda);
         itemCobroImporte.setValorCotizacion(valoCotizacion);
         itemCobroImporte.setImporte(importe);
         itemCobroImporte.setImportems(importems);
         itemCobroImporte.setImporteCobrado(cobrado);
         itemCobroImporte.setMonedaVuelto(monedaVuelto);
         itemCobroImporte.setCotizacionVuelto(cotizacionVuelto);
         itemCobroImporte.setVuelto(vuelto);
         itemCobroImporte.setVueltoms(vueltoms);
         itemCobroImporte.setBoleta(boleta);
         this.items.add(itemCobroImporte);
      }
   }

   public void ModificarPrimero(Cuenta cuenta, BigDecimal importe, BigDecimal importeCobrado, BigDecimal vuelto) {
      Optional<ItemCobroImporte> optionalIndice = this.items.stream().filter(i -> this.items.indexOf(i) == 0).findAny();
      ItemCobroImporte itemCobroImporte = optionalIndice.get();
      itemCobroImporte.setCuenta(cuenta);
      itemCobroImporte.setImporte(importe);
      itemCobroImporte.setImporteCobrado(importeCobrado);
      itemCobroImporte.setVuelto(vuelto);
   }

   public BigDecimal getTotalImporteCobrado() {
      BigDecimal importe = this.items.stream().map(ItemCobroImporte::getImporte).reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal cobrado = this.items.stream().map(ItemCobroImporte::getImporteCobrado).reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal total = importe.subtract(cobrado);
      return total.setScale(0, RoundingMode.HALF_UP);
   }

   public BigDecimal getTotalTotal() {
      return this.items.stream().map(ItemCobroImporte::getImporteCobrado).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   public BigDecimal getTotalImportems() {
      return this.items.stream().map(ItemCobroImporte::getImportems).reduce(BigDecimal.ZERO, BigDecimal::add);
   }

   public List<ItemCobroImporte> getItems() {
      return this.items;
   }

   public void eliminarVacio() {
   }

   public void eliminarItemPricipal(int indice) {
      this.items.remove(indice);
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
         TablaItemCobroImporte other = (TablaItemCobroImporte)obj;
         return Objects.equals(this.uuid, other.uuid);
      }
   }
}
