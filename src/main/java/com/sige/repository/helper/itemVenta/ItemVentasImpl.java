package com.sige.repository.helper.itemVenta;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.dto.ItemVentaPorVendedorComisionDTO;
import com.sige.dto.ItemVentaPorVendedorComisionDTOMapper;
import com.sige.model.ItemVenta;
import com.sige.model.Venta;
import com.sige.repository.filter.ItemVentaFilter;

public class ItemVentasImpl implements ItemVentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemVenta> buscarPorVenta(Venta venta) {
      return this.manager.createQuery("from ItemVenta where venta = :venta", ItemVenta.class).setParameter("venta", venta).getResultList();
   }

   @Override
   public List<Object[]> totalesVenta(ItemVentaFilter itemVentaFilter) {
      String consulta = "select  \tcliente.nombre as cliente,     vendedor.nombre as vendedor,     deposito.nombre as deposito, \tDATE_FORMAT(venta.fecha, '%d/%m/%Y') as fecha, \tid_venta as venta, round(sum(costo*cantidad),0) as costo, \tround(sum(precio*cantidad),0) as precio, \tround((sum(precio*cantidad)-sum(costo*cantidad) ),0) as utilidad  from item_venta, \tcliente,     venta,     vendedor,     deposito where \tventa.id_cliente = cliente.id     and venta.id = item_venta.id_venta     and venta.id_deposito = deposito.id     and venta.id_vendedor = vendedor.id   and (venta.id = :id or :id is null) \t and (venta.fecha between(:fechaDesde)and(:fechaHasta)) group by id_venta";
      return this.manager
         .createNativeQuery(consulta)
         .setParameter("id", itemVentaFilter.getId())
         .setParameter("fechaDesde", itemVentaFilter.getFechaDesde())
         .setParameter("fechaHasta", itemVentaFilter.getFechaHasta())
         .getResultList();
   }

   @Override
   public List<ItemVentaPorVendedorComisionDTO> gentVentaByVendedorComision(Long vendedor, LocalDate fechaDesde, LocalDate fechaHasta) {
      String sql = "select \t\tdate_format(venta.fecha,'%d/%m/%Y') as fecha \t\t,cliente.nombre as cliente         ,concat('(',producto.codigo,') ',producto.nombre) as producto         ,group_concat(distinct venta.id) as venta         , sum(item_venta.cantidad) as cantidad         , sum(item_venta.cantidad * item_venta.precio) as total         , producto.comision as porc_comision         ,((sum(item_venta.cantidad * item_venta.precio))*producto.comision)/100 as comision  from venta \tjoin cliente on venta.id_cliente = cliente.id     join item_venta on venta.id = item_venta.id_venta     join producto on item_venta.id_producto = producto.id  where  \t(id_vendedor = :vendedor or :vendedor is null)     and ( fecha between(:fechaDesde)and(:fechaHasta))     group by item_venta.id_producto,venta.fecha ,cliente    order by venta.fecha asc";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("vendedor", vendedor)
         .setParameter("fechaDesde", fechaDesde)
         .setParameter("fechaHasta", fechaHasta)
         .getResultList();
      ItemVentaPorVendedorComisionDTOMapper mapper = new ItemVentaPorVendedorComisionDTOMapper();
      return mapper.mapperResults(results);
   }

   @Transactional
   @Override
   public void eliminarItemVenta(Long idVenta) {
      String sql = "delete from item_venta where id_venta = :id";
      this.manager.createNativeQuery(sql).setParameter("id", idVenta).executeUpdate();
   }
}
