package com.sige.session.cobro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.ComisionTarjeta;
import com.sige.model.ItemCobro;
import com.sige.model.Venta;

@SessionScope
@Component
public class TablaItemCobroSession {
   public Set<TablaItemCobro> tablas = new HashSet<>();

   public void adicionarItem(Venta venta, LocalDate fecha, BigDecimal importe, String UUID) {
      TablaItemCobro tabla = this.buscarTablaPorUUID(UUID);
      tabla.adicionarItem(venta, fecha, importe);
      this.tablas.add(tabla);
   }

   public void elimiarItem(int indice, String uuid) {
      TablaItemCobro tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItem(indice);
   }

   public void eliminarItemConVenta(Venta venta, String uuid) {
      TablaItemCobro tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItemConVenta(venta);
   }

   public List<ItemCobro> getItems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   public void modificarItem(Venta venta, BigDecimal importe, String uuid) {
      TablaItemCobro tabla = this.buscarTablaPorUUID(uuid);
      tabla.modificarItem(venta, importe);
   }

   public BigDecimal getTotal(String uuid) {
      return this.buscarTablaPorUUID(uuid).getTotal();
   }

   public void adicionarItemComisionTarjeta(ComisionTarjeta comisionTarjeta, LocalDate fecha, BigDecimal importe, String uuid) {
      TablaItemCobro tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItemComisionTarjeta(comisionTarjeta, fecha, importe);
   }

   private TablaItemCobro buscarTablaPorUUID(String UUID) {
      return this.tablas.stream().filter(i -> i.getUUID().equals(UUID)).findAny().orElse(new TablaItemCobro(UUID));
   }
}
