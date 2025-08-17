package com.sige.repository.helper.marca;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Marca;

public class MarcasImpl implements MarcasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Marca> buscarMarca(Marca marca) {
      return this.manager
         .createQuery("from Marca where nombre like :nombre", Marca.class)
         .setParameter("nombre", "%" + marca.getNombre() + "%")
         .getResultList();
   }
}
