package com.sige.repository.helper.moraCliente;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.MoraCliente;
import com.sige.repository.filter.MoraClienteFilter;

public class MoraClientesImpl implements MoraClientesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<MoraCliente> buscarNombreDocumento(MoraClienteFilter moraClienteFilter) {
      if (moraClienteFilter.getLimite() == null) {
         moraClienteFilter.setLimite(100);
      }

      return this.manager
         .createQuery("from MoraCliente where (cliente like :cliente or :cliente is null) or (documento =:documento or :documento is null)", MoraCliente.class)
         .setParameter("cliente", "%" + moraClienteFilter.getNombreDocumento() + "%")
         .setParameter("documento", moraClienteFilter.getNombreDocumento())
         .setMaxResults(moraClienteFilter.getLimite())
         .getResultList();
   }
}
