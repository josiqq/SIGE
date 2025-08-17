package com.sige.repository.helper.precio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PreciosImpl implements PreciosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Object[]> getPreciosMobile(Long precio) {
      String sql = "select \tproducto.codigo     ,producto.nombre     ,item_precio.precio_producto from producto left join item_precio on producto.id = item_precio.id_producto where item_precio.id_precio = :precio ";
      return this.manager.createNativeQuery(sql).setParameter("precio", precio).getResultList();
   }
}
