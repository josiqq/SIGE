package com.sige.repository.helper.itemAgenda;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.sige.model.Cliente;
import com.sige.model.Estado;

public class ItemAgendasImpl implements ItemAgendasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Transactional
   @Override
   public void modificarItem(Long id, Cliente cliente, Estado estado, String observacio) {
      this.manager
         .createQuery("update ItemAgenda set estado =:estado,cliente=:cliente,observacion = :observacion where id =:id")
         .setParameter("id", id)
         .setParameter("cliente", cliente)
         .setParameter("estado", estado)
         .setParameter("observacion", observacio)
         .executeUpdate();
   }
}
