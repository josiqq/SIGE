package com.sige.repository.helper.cuentaPorCobrar;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.dto.CuentaPorCobrarDTO;
import com.sige.dto.CuentaPorCobrarMapper;
import com.sige.model.CuentaPorCobrar;
import com.sige.model.Venta;
import com.sige.repository.filter.CuentaPorCobrarFilter;

public class CuentaPorCobrarsImpl implements CuentaPorCobrarsQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public CuentaPorCobrar buscarPorVenta(Venta venta) {
      return (CuentaPorCobrar)this.manager
         .createQuery("from CuentaPorCobrar where venta = :venta and importe>importeCobrado", CuentaPorCobrar.class)
         .setParameter("venta", venta)
         .getSingleResult();
   }

   @Override
   public List<Object[]> getCuentasByCliente(Long id, Long moneda) {
      String sql = "select cuenta_por_cobrar.id,date_format(cuenta_por_cobrar.fecha,'%d/%m/%Y'),cuenta_por_cobrar.importe,cuenta_por_cobrar.importe_cobrado,(cuenta_por_cobrar.importe-cuenta_por_cobrar.importe_cobrado) as saldo,cuenta_por_cobrar.id_venta,venta.id_cliente,venta.factura, venta.nro_factura,venta.condicion_venta from venta,cuenta_por_cobrar where cuenta_por_cobrar.id_venta = venta.id and venta.id_cliente =:id_cliente   and cuenta_por_cobrar.id_moneda = :moneda  and (abs(cuenta_por_cobrar.importe)-cuenta_por_cobrar.importe_cobrado)>0";
      return this.manager.createNativeQuery(sql).setParameter("id_cliente", id).setParameter("moneda", moneda).getResultList();
   }

   @Override
   public List<CuentaPorCobrarDTO> getCuentaPorCobrarDTO(CuentaPorCobrarFilter cuentaPorCobrarFilter) {
      int condi;
      if (cuentaPorCobrarFilter.getCondicionVenta().getDescripcion() == "Contado") {
         condi = 0;
      } else {
         condi = 1;
      }

      String sql = "select \tdate_format(cuenta_por_cobrar.fecha,'%d/%m/%Y') as fecha     ,cliente.nombre as cliente     ,venta.id as venta     ,cuenta_por_cobrar.importe     ,cuenta_por_cobrar.importe_cobrado  ,(cuenta_por_cobrar.importe-cuenta_por_cobrar.importe_cobrado) as saldo from \tcuenta_por_cobrar left join venta on cuenta_por_cobrar.id_venta = venta.id \tleft join cliente on venta.id_cliente = cliente.id     where (venta.id_cliente = :id_cliente or :id_cliente is null )     and cuenta_por_cobrar.importe_cobrado < importe     and (venta.id = :id_venta or :id_venta is null)    and cuenta_por_cobrar.fecha between(:fechaDesde)and(:fechaHasta)    and venta.condicion_venta = :condicion";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("id_cliente", cuentaPorCobrarFilter.getIdCliente())
         .setParameter("id_venta", cuentaPorCobrarFilter.getIdVenta())
         .setParameter("fechaDesde", cuentaPorCobrarFilter.getFechaDesde())
         .setParameter("fechaHasta", cuentaPorCobrarFilter.getFechaHasta())
         .setParameter("condicion", condi)
         .getResultList();
      CuentaPorCobrarMapper mapper = new CuentaPorCobrarMapper();
      return mapper.mapearResult(results);
   }
}
