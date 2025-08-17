package com.sige.repository.helper.cajero;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Cajero;
import com.sige.repository.filter.CajeroFilter;

public class CajerosImpl implements CajerosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Cajero> buscarCajerosActivos() {
      return this.manager.createQuery("from Cajero where activo = true", Cajero.class).getResultList();
   }

   @Override
   public List<Cajero> buscarPorNombreDocumento(CajeroFilter cajeroFilter) {
      return this.manager
         .createQuery("from Cajero where (nombre like :nombre or nombre is null) or (documento = :documento or :documento is null)", Cajero.class)
         .setParameter("nombre", "%" + cajeroFilter.getNombreDocumento() + "%")
         .setParameter("documento", cajeroFilter.getNombreDocumento())
         .getResultList();
   }
}
