package com.sige.repository.helper.proveedor;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Proveedor;

public class ProveedoresImpl implements ProveedoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Proveedor> buscarPorNombreODocumento(String nombreDocumento) {
      return this.manager
         .createQuery("from Proveedor where (nombre like :nombreDocumento or documento like :nombreDocumento)and activo =true", Proveedor.class)
         .setParameter("nombreDocumento", "%" + nombreDocumento + "%")
         .getResultList();
   }
}
