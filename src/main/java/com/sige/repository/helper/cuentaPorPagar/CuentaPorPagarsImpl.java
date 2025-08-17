package com.sige.repository.helper.cuentaPorPagar;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.dto.CuentaPorPagarDTO;
import com.sige.dto.CuentaPorPagarMapper;
import com.sige.model.CuentaPorPagar;
import com.sige.model.Moneda;
import com.sige.model.Proveedor;
import com.sige.repository.filter.CuentaPorPagarFilter;

public class CuentaPorPagarsImpl implements CuentaPorPagarsQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<CuentaPorPagar> getCuentaPorProveedores(Proveedor proveedor, Moneda moneda) {
      return this.manager
         .createQuery("from CuentaPorPagar where compra.proveedor = :proveedor  and (importe-importePagado)>0 and moneda = :moneda", CuentaPorPagar.class)
         .setParameter("proveedor", proveedor)
         .setParameter("moneda", moneda)
         .getResultList();
   }

   @Override
   public List<CuentaPorPagarDTO> getCuetasPorPagarDTO(CuentaPorPagarFilter cuentaPorPagarFilter) {
      String sql = "select date_format(cuenta_por_pagar.fecha,'%d/%m/%Y') as fecha  \t,proveedor.nombre as proveedor     ,compra.id as compra     ,cuenta_por_pagar.importe as importe     ,cuenta_por_pagar.importe_pagado as importe_pagado     ,(cuenta_por_pagar.importe- cuenta_por_pagar.importe_pagado) as saldo  , date_format(cuenta_por_pagar.vencimiento,'%d/%m/%Y') as vencimiento  from  \tcuenta_por_pagar left join compra on compra.id = cuenta_por_pagar.id_compra \tleft join proveedor on compra.id_proveedor = proveedor.id  where  \t(cuenta_por_pagar.id = :idCompra or :idCompra is null)     and (compra.id_proveedor = :idProveedor or :idProveedor is null)     and cuenta_por_pagar.importe_pagado < cuenta_por_pagar.importe     and (cuenta_por_pagar.fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)  and ((cuenta_por_pagar.vencimiento between(:vencimientoDesde)and(:vencimientoHasta) or :vencimientoDesde is null or :vencimientoHasta is null))";
      List<Object[]> result = this.manager
         .createNativeQuery(sql)
         .setParameter("idCompra", cuentaPorPagarFilter.getIdCompra())
         .setParameter("idProveedor", cuentaPorPagarFilter.getIdProveedor())
         .setParameter("fechaDesde", cuentaPorPagarFilter.getFechaDesde())
         .setParameter("fechaHasta", cuentaPorPagarFilter.getFechaHasta())
         .setParameter("vencimientoDesde", cuentaPorPagarFilter.getVencimientoDesde())
         .setParameter("vencimientoHasta", cuentaPorPagarFilter.getVencimientoHasta())
         .getResultList();
      CuentaPorPagarMapper mapper = new CuentaPorPagarMapper();
      return mapper.mapearLista(result);
   }
}
