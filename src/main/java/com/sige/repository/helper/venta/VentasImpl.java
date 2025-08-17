package com.sige.repository.helper.venta;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.dto.FacturaDto;
import com.sige.dto.FacturaMapper;
import com.sige.model.ItemVenta;
import com.sige.model.Timbrado;
import com.sige.model.Venta;
import com.sige.repository.filter.VentaFilter;
import com.sige.repository.filter.VentaMobileFilter;

public class VentasImpl implements VentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Venta> buscarVenta(VentaFilter ventaFilter) {
      String sql = "from Venta where (id=:id or :id is null) and (fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null))  order by fecha desc";
      return this.manager
         .createQuery(sql, Venta.class)
         .setParameter("fechaDesde", ventaFilter.getFechaDesde())
         .setParameter("fechaHasta", ventaFilter.getFechaHasta())
         .setParameter("id", ventaFilter.getId())
         .setMaxResults(ventaFilter.getLimite())
         .getResultList();
   }

   @Override
   public List<Object[]> getVentaPorProducto(VentaFilter ventaFilter) {
      String sql = "select \titem_venta.id_producto \t,producto.codigo \t,producto.nombre as producto \t,sum(item_venta.cantidad) as cantidad     ,round(sum(item_venta.costo*item_venta.cantidad),0) as costo \t,round(sum(item_venta.cantidad*item_venta.precio),0) as total     ,round((sum(item_venta.cantidad*item_venta.precio)-sum(item_venta.costo*item_venta.cantidad)),0)as utilidad \t,date_format(venta.fecha,\"%d/%m/%y\") as fecha,producto.pesable  as pesable from \tventa,\titem_venta,    producto where \tventa.id = item_venta.id_venta \tand item_venta.id_producto = producto.id \tand fecha between(:fechaDesde)and(:fechaHasta) \tand (producto.id = :id or :id is null) \tgroup by producto.id,venta.fecha";
      return this.manager
         .createNativeQuery(sql)
         .setParameter("fechaDesde", ventaFilter.getFechaDesde())
         .setParameter("fechaHasta", ventaFilter.getFechaHasta())
         .setParameter("id", this.isProducto(ventaFilter) ? ventaFilter.getProducto().getId() : null)
         .getResultList();
   }

   public boolean isProducto(VentaFilter ventaFilter) {
      return ventaFilter.getProducto() != null;
   }

   @Override
   public List<FacturaDto> getFactura(Long id) {
      String sql = "select \tventa.id,    cliente.nombre as cliente,     cliente.documento,    concat(timbrado.expedicion,'-',right(concat('0000000',venta.nro_factura),7)) as factura,\tdate_format(venta.fecha,'%d/%m/%Y') as fecha,    if(venta.condicion_venta =0,'CONTADO','CREDITO')as condicion_venta,    vendedor.nombre as vendedor    ,producto.codigo,    producto.nombre,    item_venta.cantidad,    item_venta.precio,    (item_venta.cantidad*item_venta.precio) as sub_total,    producto.gravado,    case\t\twhen producto.gravado = 10 then\t\t\t((item_venta.cantidad*precio)/1.1)\t\twhen producto.gravado = 5 then\t\t\t0    end as gravado_10,    case\t\twhen producto.gravado = 10 then\t\t\t0\t\twhen producto.gravado = 5 then\t\t\t((item_venta.cantidad*precio)/1.05)    end as gravado_5,    case\t\twhen producto.gravado = 10 then\t\t\t((item_venta.cantidad*precio)/11)\t\twhen producto.gravado = 5 then\t\t\t0    end as impuesto_10,    case\t\twhen producto.gravado = 10 then\t\t\t0\t\twhen producto.gravado = 5 then\t\t\t((item_venta.cantidad*precio)/21)    end as impuesto_5,    timbrado.numero as timbrado,    date_format(timbrado.fecha_inicio,'%d/%m/%Y') as fecha_inicio,    date_format(timbrado.fecha_fin,'%d/%m/%Y') as fecha_fin,    producto.pesable from venta,cliente,timbrado,vendedor,item_venta,producto  where venta.id_vendedor = vendedor.id and venta.id_cliente = cliente.id and venta.id_timbrado = timbrado.id \tand venta.id = item_venta.id_venta and item_venta.id_producto=producto.id\tand venta.id = :id";
      List<Object[]> results = this.manager.createNativeQuery(sql).setParameter("id", id).getResultList();
      FacturaMapper facturaMapper = new FacturaMapper();
      return facturaMapper.mapResultToList(results);
   }

   @Override
   public Integer getNroFactura(Timbrado timbrado) {
      String sql = "select max(nroFactura) from Venta where timbrado = :timbrado ";
      return (Integer)this.manager.createQuery(sql, Integer.class).setParameter("timbrado", timbrado).getSingleResult();
   }

   @Transactional
   @Override
   public void updateNroFactura(Timbrado timbrado, Venta venta, Integer nroFactura) {
      this.manager
         .createQuery("update Venta set timbrado = :timbrado, nroFactura =:nroFactura where id =:id")
         .setParameter("id", venta.getId())
         .setParameter("nroFactura", nroFactura)
         .setParameter("timbrado", timbrado)
         .executeUpdate();
   }

   @Transactional
   @Override
   public void updateImpreso(Long id) {
      String sql = " update Venta set impreso = true where id =:id";
      this.manager.createQuery(sql).setParameter("id", id).executeUpdate();
   }

   @Override
   public List<Venta> getVentaMobile(VentaMobileFilter ventaMobileFilter) {
      String sql = "from Venta where (id=:id or :id is null) and (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)  and (cliente = :cliente or :cliente is null)";
      return this.manager
         .createQuery(sql, Venta.class)
         .setParameter("id", ventaMobileFilter.getId())
         .setParameter("fechaDesde", ventaMobileFilter.getFechaDesde())
         .setParameter("fechaHasta", ventaMobileFilter.getFechaHasta())
         .setParameter("cliente", ventaMobileFilter.getCliente())
         .getResultList();
   }

   @Override
   public List<ItemVenta> getItemVentaByVenta(Venta venta) {
      String sql = "from ItemVenta where venta =:venta";
      return this.manager.createQuery(sql, ItemVenta.class).setParameter("venta", venta).getResultList();
   }

   @Override
   public List<Venta> getVentaNc(VentaFilter ventaFilter) {
      return this.manager
         .createQuery(
            "from Venta where (id=:id or :id is null) and (fecha between(:fechaDesde)and(:fechaHasta) or (:fechaDesde is null or :fechaHasta is null)) and nc = true",
            Venta.class
         )
         .setParameter("fechaDesde", ventaFilter.getFechaDesde())
         .setParameter("fechaHasta", ventaFilter.getFechaHasta())
         .setParameter("id", ventaFilter.getId())
         .setMaxResults(ventaFilter.getLimite())
         .getResultList();
   }
}
