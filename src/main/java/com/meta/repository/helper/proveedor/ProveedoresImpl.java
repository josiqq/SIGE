package com.meta.repository.helper.proveedor;

import com.meta.modelo.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
