package com.sige.repository.helper.deposito;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Deposito;

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
