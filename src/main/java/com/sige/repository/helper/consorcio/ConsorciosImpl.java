package com.sige.repository.helper.consorcio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.dto.ClienteConsorcioDTO;
import com.sige.dto.ClienteConsorcioMapper;
import com.sige.model.Consorcio;

public class ConsorciosImpl implements ConsorciosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Consorcio> getConsorcios(Consorcio consorcio) {
      String sql = "from Consorcio where (fechaInicio >= :fechaInicio and fechaFin <= :fechaFin)";
      return this.manager
         .createQuery(sql, Consorcio.class)
         .setParameter("fechaInicio", consorcio.getFechaInicio())
         .setParameter("fechaFin", consorcio.getFechaFin())
         .getResultList();
   }

   @Override
   public List<Consorcio> getConsorciosPendientes() {
      String sql = "from Consorcio where terminado =0";
      return this.manager.createQuery(sql, Consorcio.class).getResultList();
   }

   @Override
   public List<ClienteConsorcioDTO> getItemConsorcioByClienteConsorcio(Long consorcio, String cliente) {
      String sql = "select\tcliente.id\t,cliente.nombre from \titem_consorcio  left join(cliente) on (item_consorcio.id_cliente = cliente.id) where item_consorcio.id_consorcio = :consorcio and cliente.nombre like :cliente";
      List<Object[]> results = this.manager
         .createNativeQuery(sql)
         .setParameter("consorcio", consorcio)
         .setParameter("cliente", "%" + cliente + "%")
         .getResultList();
      ClienteConsorcioMapper clienteConsorcioMapper = new ClienteConsorcioMapper();
      return clienteConsorcioMapper.mapearObjecto(results);
   }
}
