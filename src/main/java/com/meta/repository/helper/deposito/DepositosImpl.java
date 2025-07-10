package com.meta.repository.helper.deposito;

import com.meta.modelo.Deposito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DepositosImpl implements DepositosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Deposito> buscarDeposito(Deposito deposito) {
      return this.manager
         .createQuery("from Deposito where nombre like :nombre", Deposito.class)
         .setParameter("nombre", "%" + deposito.getNombre() + "%")
         .getResultList();
   }

   @Override
   public List<Deposito> buscarDepositoActivo() {
      return this.manager.createQuery("from Deposito where activo = true", Deposito.class).getResultList();
   }
}
