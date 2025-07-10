package com.meta.repository.helper.ajustePrecio;

import com.meta.modelo.AjustePrecio;
import com.meta.modelo.ItemAjustePrecio;
import com.meta.repository.filter.AjustePrecioFilter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AjustePreciosImpl implements AjustePreciosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<AjustePrecio> buscarAjustePrecio(AjustePrecioFilter ajustePrecioFilter) {
      return this.manager
         .createQuery(
            "from AjustePrecio where (precio= :precio or :precio is null)  and( fecha between(:fechaDesde)and(:fechaHasta) or(:fechaDesde is null or :fechaHasta is null))",
            AjustePrecio.class
         )
         .setParameter("precio", ajustePrecioFilter.getPrecio())
         .setParameter("fechaDesde", ajustePrecioFilter.getFechaDesde())
         .setParameter("fechaHasta", ajustePrecioFilter.getFechaHasta())
         .getResultList();
   }

   @Override
   public List<ItemAjustePrecio> buscarItemAjustePrecio(AjustePrecioFilter filter) {
      String sql = "FROM ItemAjustePrecio WHERE (:id IS NULL OR ajustePrecio.id = :id) AND (:fechaDesde IS NULL OR ajustePrecio.fecha >= :fechaDesde) AND (:fechaHasta IS NULL OR ajustePrecio.fecha <= :fechaHasta) AND (:precio IS NULL OR ajustePrecio.precio = :precio)";
      return this.manager
         .createQuery(sql, ItemAjustePrecio.class)
         .setParameter("id", filter.getId())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setParameter("precio", filter.getPrecio())
         .getResultList();
   }
}
