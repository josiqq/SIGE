package com.sige.repository.helper.planilla;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.dto.PlanillaImporteDTO;
import com.sige.dto.PlanillaImporteDTOMapper;
import com.sige.model.Cuenta;
import com.sige.model.ItemPlanillaRendicion;
import com.sige.model.Planilla;
import com.sige.repository.filter.PlanillaFilter;

public class PlanillasImpl implements PlanillasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Optional<Planilla> getByCuenta(Cuenta cuenta) {
      String sql = "from Planilla where cuenta = :cuenta and cerrado = false";

      try {
         Planilla planilla = (Planilla)this.manager.createQuery(sql, Planilla.class).setParameter("cuenta", cuenta).getSingleResult();
         return Optional.of(planilla);
      } catch (NoResultException var4) {
         return Optional.empty();
      }
   }

   @Override
   public List<Planilla> getPlanillas(PlanillaFilter planillaFilter) {
      String sql = "from Planilla where (id = :id or :id is null) \tand (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)";
      return this.manager
         .createQuery(sql, Planilla.class)
         .setParameter("id", planillaFilter.getId())
         .setParameter("fechaDesde", planillaFilter.getFechaDesde())
         .setParameter("fechaHasta", planillaFilter.getFechaHasta())
         .setMaxResults(planillaFilter.getLimite())
         .getResultList();
   }

   @Override
   public List<Planilla> getPlanillasByFecha(LocalDate fecha) {
      String sql = "from Planilla where fecha = :fecha";
      return this.manager.createQuery(sql, Planilla.class).setParameter("fecha", fecha).getResultList();
   }

   @Override
   public List<Planilla> getPlanillasAbiertas() {
      String sql = "from Planilla where cerrado = false";
      return this.manager.createQuery(sql, Planilla.class).getResultList();
   }

   @Override
   public List<PlanillaImporteDTO> getPlanillasImportes(Long planilla) {
      String sql = " select condicion.nombre as condicion, moneda.sigla as moneda   \t\t\t\t\t    ,sum(item_cobro_importe.importe) as cobro   \t\t\t\t\t    ,ifnull((select sum(item_planilla.importe) from item_planilla where item_planilla.id_condicion = condicion.id    \t\t\t\t\t    and item_planilla.id_moneda = moneda.id and item_planilla.id_planilla = cobro.id_planilla),0) as apertura   \t\t\t\t\t     ,sum(case     \t\t\t\t\t    \t\t\t\t\t  \t\twhen item_cobro_importe.id_moneda <> item_cobro_importe.id_moneda_vuelto then     \t\t\t\t\t    \t\t\t\t\t  \t\t\t f_cotizar(item_cobro_importe.id_moneda_vuelto, item_cobro_importe.id_moneda,cobro.fecha, item_cobro_importe.vuelto, 0)     \t\t\t\t\t    \t\t\t\t\t  \t\telse     \t\t\t\t\t    \t\t\t\t\t  \t\t\t0     \t\t\t\t\t    \t\t\t\t\t      end) as mas_vuelto     \t\t\t\t\t    \t\t,ifnull((select sum(vuelto) from item_cobro_importe ici join cobro c on c.id = ici.id_cobro      \t\t\t\t\t    \t\t\t\t\t  \t\t\twhere ici.id_moneda_vuelto = item_cobro_importe.id_moneda      \t\t\t\t\t    \t\t\t\t\t              and ici.id_condicion = item_cobro_importe.id_condicion     \t\t\t\t\t    \t\t\t\t\t              and ici.id_moneda <>ici.id_moneda_vuelto and c.id_planilla = cobro.id_planilla     \t\t\t\t\t    \t\t\t\t\t  \t\t\tand condicion.condicion_cobro = 0),0) as menos_vuelto \t\t\t\t\t\t\t,ifnull((select sum(importe) from transferencia_efectivo where transferencia_efectivo.id_planilla_destino = cobro.id_planilla and                              transferencia_efectivo.id_moneda = moneda.id                             and transferencia_efectivo.id_condicion = condicion.id),0) as trans_credi                                                          ,ifnull((select sum(importe) from transferencia_efectivo where transferencia_efectivo.id_planilla = cobro.id_planilla and                              transferencia_efectivo.id_moneda = moneda.id                             and transferencia_efectivo.id_condicion = condicion.id),0) as trans_debi                              \t\t\t\t\t    \t,f_cotizacion(parametro_venta.id_moneda,item_cobro_importe.id_moneda,cobro.fecha) as cotizacion     \t\t\t\t\t    from parametro_venta, cobro join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro   \t\t\t\t\t    join condicion on item_cobro_importe.id_condicion = condicion.id   \t\t\t\t\t    join moneda on item_cobro_importe.id_moneda = moneda.id   \t\t\t\t\t    where cobro.id_planilla = :planilla  \t\t\t\t\t    group by condicion, moneda,apertura,menos_vuelto,trans_credi,trans_debi,cotizacion                         union all                           select condicion.nombre as condicion    \t\t\t\t\t   \t,moneda.sigla as moneda   \t\t\t\t\t       ,0 as cobro   \t\t\t\t\t       ,sum(item_planilla.importe) as apertura   \t\t\t\t\t       ,0 as mas_vuelto   \t\t\t\t\t       ,ifnull((select sum(vuelto) from item_cobro_importe ici join cobro c on c.id = ici.id_cobro      \t\t\t\t\t   \t\t\t\t\t  \t\t\twhere ici.id_moneda_vuelto = moneda.id      \t\t\t\t\t   \t\t\t\t\t              and ici.id_condicion = condicion.id     \t\t\t\t\t   \t\t\t\t\t              and ici.id_moneda <>ici.id_moneda_vuelto and c.id_planilla = item_planilla.id_planilla    \t\t\t\t\t   \t\t\t\t\t  \t\t\tand condicion.condicion_cobro = 0),0) as menos_vuelto   \t\t\t\t\t\t,0 as trans_credi                         ,0 as trans_debi \t\t\t\t\t   \t,f_cotizacion(parametro_venta.id_moneda,item_planilla.id_moneda,planilla.fecha) as cotizacion     \t\t\t\t\t   from parametro_venta, planilla join item_planilla on planilla.id = item_planilla.id_planilla   \t\t\t\t\t    join condicion on item_planilla.id_condicion = condicion.id   \t\t\t\t\t   join moneda on moneda.id = item_planilla.id_moneda   \t\t\t\t\t   where    \t\t\t\t\t   not exists(select * from cobro join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro where cobro.id_planilla = item_planilla.id_planilla    \t\t\t\t\t   and item_cobro_importe.id_moneda = item_planilla.id_moneda and item_cobro_importe.id_condicion = item_planilla.id_condicion) and    \t\t\t\t\t   item_planilla.id_planilla =:planilla \t\t\t\t\t   group by condicion, moneda,cobro,mas_vuelto,menos_vuelto,trans_credi,trans_debi,cotizacion                        union all                        select  \t\t\t\t\t\tcondicion.nombre as condicion,                         moneda.sigla as moneda                         ,0 as cobro                         ,0 as apertura                         ,0 as mas_vuelto                         ,ifnull((select sum(vuelto) from item_cobro_importe ici join cobro c on c.id = ici.id_cobro      \t\t\t\t\t   \t\t\t\t\t  \t\t\twhere ici.id_moneda_vuelto = moneda.id      \t\t\t\t\t   \t\t\t\t\t              and ici.id_condicion = condicion.id     \t\t\t\t\t   \t\t\t\t\t              and ici.id_moneda <>ici.id_moneda_vuelto and c.id_planilla = transferencia_efectivo.id_planilla_destino    \t\t\t\t\t   \t\t\t\t\t  \t\t\tand condicion.condicion_cobro = 0),0) as menos_vuelto  \t\t\t\t\t\t, sum(case  \t\t\t\t\t\t\t\t\twhen transferencia_efectivo.id_planilla_destino is not null then                                     ifnull(importe,0)                                     else                                     0                         end) as trans_credi                         , sum(case  \t\t\t\t\t\t\t\t\twhen transferencia_efectivo.id_planilla is not null then                                     ifnull(importe,0)                                     else                                     0                         end) as trans_debi \t\t\t\t\t\t,f_cotizacion(parametro_venta.id_moneda,transferencia_efectivo.id_moneda,transferencia_efectivo.fecha) as cotizacion \t\t\t\t\tfrom parametro_venta,transferencia_efectivo join condicion on transferencia_efectivo.id_condicion = condicion.id \t\t\t\t\t\tjoin moneda on transferencia_efectivo.id_moneda = moneda.id \t\t\t\t\twhere    \t\t\t\t\t   not exists(select * from cobro join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro                         where ((cobro.id_planilla = transferencia_efectivo.id_planilla_destino)or(cobro.id_planilla = transferencia_efectivo.id_planilla))    \t\t\t\t\t   and item_cobro_importe.id_moneda = transferencia_efectivo.id_moneda and item_cobro_importe.id_condicion = transferencia_efectivo.id_condicion) and    \t\t\t\t\t   (transferencia_efectivo.id_planilla =:planilla or transferencia_efectivo.id_planilla_destino =:planilla) \t\t\t\t\t   group by condicion, moneda,cobro,apertura,mas_vuelto,menos_vuelto,cotizacion";
      List<Object[]> results = this.manager.createNativeQuery(sql).setParameter("planilla", planilla).getResultList();
      PlanillaImporteDTOMapper mapper = new PlanillaImporteDTOMapper();
      return mapper.mapearResults(results);
   }

   @Transactional
   @Override
   public void eliminarItemPlanillaRendicion(Long idPlanilla) {
      this.manager
         .createNativeQuery("delete from item_planilla_rendicion where id_planilla = :idPlanilla")
         .setParameter("idPlanilla", idPlanilla)
         .executeUpdate();
   }

   @Override
   public List<ItemPlanillaRendicion> getItemPlanillaRendiciones(Long idPlanilla) {
      String sql = "from ItemPlanillaRendicion where planilla.id = :idPlanilla";
      return this.manager.createQuery(sql, ItemPlanillaRendicion.class).setParameter("idPlanilla", idPlanilla).getResultList();
   }
}
