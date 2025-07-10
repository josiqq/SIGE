package com.meta.repository.helper.moraCliente;

import com.meta.modelo.MoraCliente;
import com.meta.repository.filter.MoraClienteFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
