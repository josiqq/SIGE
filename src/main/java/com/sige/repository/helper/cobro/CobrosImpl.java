package com.sige.repository.helper.cobro;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.dto.cobro.CobroRealizadoDTO;
import com.sige.dto.cobro.CobroRealizadoDTOMapper;
import com.sige.model.Cobro;
import com.sige.model.ItemCobroImporte;
import com.sige.repository.filter.CobroFilter;

public class CobrosImpl implements CobrosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Cobro> getCobros(CobroFilter cobroFilter) {
      String sql = "from Cobro where (id= :id or :id is null)  and (fecha between(:fechaDesde)and(:fechaHasta) or :fechaDesde is null or :fechaHasta is null)";
      return this.manager
         .createQuery(sql, Cobro.class)
         .setParameter("id", cobroFilter.getId())
         .setParameter("fechaDesde", cobroFilter.getFechaDesde())
         .setParameter("fechaHasta", cobroFilter.getFechaHasta())
         .setMaxResults(cobroFilter.getLimite())
         .getResultList();
   }

   @Override
   public List<Object[]> getArqueoCaja(String sql, Long planilla) {
      return this.manager.createNativeQuery(sql).setParameter("planilla", planilla).getResultList();
   }

   @Override
   public List<ItemCobroImporte> getItemCobroImporte(Long id) {
      String sql = "from ItemCobroImporte where cobro.id = :id";
      return this.manager.createQuery(sql, ItemCobroImporte.class).setParameter("id", id).getResultList();
   }

   @Override
   public List<CobroRealizadoDTO> getCobrosRealizados(CobroFilter filter) {
      String sql = "select  \tcobro.id as cobro     ,DATE_FORMAT(cobro.fecha, '%d/%m/%Y') as fecha     ,cuenta.nombre as cuenta     ,cliente.nombre as cliente     ,condicion.nombre as condicion     ,moneda.nombre as moneda     ,moneda.sigla as sigla     ,item_cobro_importe.importe  from cobro join cuenta on cobro.id_cuenta = cuenta.id  \tjoin cliente on cobro.id_cliente =cliente.id     join item_cobro_importe on cobro.id = item_cobro_importe.id_cobro     join condicion on condicion.id = item_cobro_importe.id_condicion     join moneda on moneda.id = item_cobro_importe.id_moneda  where (fecha between(:fechaDesde)and(:fechaHasta) )";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .getResultList();
      CobroRealizadoDTOMapper mapper = new CobroRealizadoDTOMapper();
      return mapper.mapper(results);
   }

   @Override
   public List<Object[]> getCobrosRealizadosMoneda(String sql) {
      return this.manager.createNativeQuery(sql).getResultList();
   }
}
