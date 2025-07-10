package com.meta.repository.helper.marca;

import com.meta.modelo.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
