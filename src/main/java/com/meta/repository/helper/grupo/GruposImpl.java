package com.meta.repository.helper.grupo;

import com.meta.modelo.Grupo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
