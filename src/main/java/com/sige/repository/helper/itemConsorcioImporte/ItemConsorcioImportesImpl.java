package com.sige.repository.helper.itemConsorcioImporte;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.sige.model.Cliente;
import com.sige.model.Consorcio;
import com.sige.model.Cuenta;
import com.sige.model.ItemConsorcioImporte;

public class ItemConsorcioImportesImpl implements ItemConsorcioImportesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<ItemConsorcioImporte> getItemConsorcioImporteByCliente(Cliente cliente, Consorcio consorcio) {
      String sql = "from ItemConsorcioImporte where itemConsorcio.cliente = :cliente and itemConsorcio.consorcio =:consorcio";
      return this.manager.createQuery(sql, ItemConsorcioImporte.class).setParameter("cliente", cliente).setParameter("consorcio", consorcio).getResultList();
   }

   @Transactional
   @Override
   public void updateImporte(Long id, BigDecimal montoCobrado, BigDecimal saldo, Cuenta cuenta) {
      String sql = "Update ItemConsorcioImporte set montoCobrado = :montoCobrado, saldo = :saldo,cuenta = :cuenta where id =:id";
      this.manager
         .createQuery(sql)
         .setParameter("id", id)
         .setParameter("montoCobrado", montoCobrado)
         .setParameter("saldo", saldo)
         .setParameter("cuenta", cuenta)
         .executeUpdate();
   }
}
