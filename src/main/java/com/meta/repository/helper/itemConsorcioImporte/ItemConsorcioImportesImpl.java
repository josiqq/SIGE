package com.meta.repository.helper.itemConsorcioImporte;

import com.meta.modelo.Cliente;
import com.meta.modelo.Consorcio;
import com.meta.modelo.Cuenta;
import com.meta.modelo.ItemConsorcioImporte;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
