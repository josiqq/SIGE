package com.sige.session.cobro;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.sige.model.Banco;
import com.sige.model.Condicion;
import com.sige.model.Cuenta;
import com.sige.model.ItemCobroImporte;
import com.sige.model.Moneda;

@SessionScope
@Component
public class TablaItemCobroImporteSession {
   public Set<TablaItemCobroImporte> tablas = new HashSet<>();

   public List<ItemCobroImporte> getItems(String uuid) {
      return this.buscarTablaPorUUID(uuid).getItems();
   }

   public void eliminarVacio(String uuid) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarVacio();
   }

   public void modificarPrimero(Cuenta cuenta, BigDecimal importe, BigDecimal importeCobrado, BigDecimal vuelto, String uuid) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      tabla.ModificarPrimero(cuenta, importe, importeCobrado, vuelto);
   }

   public BigDecimal getTotal(String uuid) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      return tabla.getTotalImporteCobrado();
   }

   public BigDecimal getTotalImportems(String uuid) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      return tabla.getTotalImportems();
   }

   public BigDecimal getTotalTotal(String uuid) {
      return this.buscarTablaPorUUID(uuid).getTotalTotal();
   }

   public void adicionarItemVentaMB(
      Cuenta cuenta, BigDecimal importe, BigDecimal importeCobrado, BigDecimal vuelto, Condicion condicion, Moneda moneda, Moneda monedaVuelto, String uuid
   ) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItemVentaMB(cuenta, importe, importeCobrado, vuelto, condicion, moneda, monedaVuelto);
      this.tablas.add(tabla);
   }

   public void adicionarItemPrincipal(
      Cuenta cuenta,
      Condicion condicion,
      Moneda moneda,
      BigDecimal valoCotizacion,
      BigDecimal importe,
      BigDecimal importems,
      BigDecimal importeCobrado,
      Moneda monedaVuelto,
      BigDecimal cotizacionVuelto,
      BigDecimal vuelto,
      BigDecimal vueltoms,
      Banco banco,
      String boleta,
      String uuid
   ) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      tabla.adicionarItemPrincipal(
         cuenta, condicion, moneda, valoCotizacion, importe, importems, importeCobrado, monedaVuelto, cotizacionVuelto, vuelto, vueltoms, banco, boleta
      );
      this.tablas.add(tabla);
   }

   public void eliminarItemImportePrincipal(int indice, String uuid) {
      TablaItemCobroImporte tabla = this.buscarTablaPorUUID(uuid);
      tabla.eliminarItemPricipal(indice);
   }

   private TablaItemCobroImporte buscarTablaPorUUID(String uuid) {
      return this.tablas.stream().filter(i -> i.getUuid().equals(uuid)).findAny().orElse(new TablaItemCobroImporte(uuid));
   }
}
