package com.sige.repository.helper.equipo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Equipo;

public class EquiposImpl implements EquiposQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Equipo> BuscarPorNombre(Equipo equipo) {
      return this.manager
         .createQuery("from Equipo where (nombre like :nombre or :nombre is null)", Equipo.class)
         .setParameter("nombre", "%" + equipo.getNombre() + "%")
         .getResultList();
   }

   @Override
   public String buscarFoto(Long id) {
      return (String)this.manager.createQuery("select foto from Equipo where id = :id", String.class).setParameter("id", id).getSingleResult();
   }
}
