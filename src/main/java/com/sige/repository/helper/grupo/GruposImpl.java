package com.sige.repository.helper.grupo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Grupo;

public class GruposImpl implements GruposQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Grupo> buscarPorNombre(Grupo grupo) {
      return this.manager
         .createQuery("from Grupo where nombre like :nombre", Grupo.class)
         .setParameter("nombre", "%" + grupo.getNombre() + "%")
         .getResultList();
   }
}
