package com.meta.repository.helper.precio;

import com.meta.modelo.ItemPrecio;
import com.meta.modelo.Precio;
import com.meta.modelo.Producto;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ItemPreciosImpl implements ItemPreciosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public ItemPrecio buscarprecioProducto(Precio precio, Producto producto) {
      return (ItemPrecio)this.manager
         .createQuery("from ItemPrecio where precio = :precio and producto = :producto ", ItemPrecio.class)
         .setParameter("precio", precio)
         .setParameter("producto", producto)
         .getSingleResult();
   }

   @Override
   public List<ItemPrecio> buscarPorProductoPrecio(String nombreCodigo, Precio precio) {
      return this.manager
         .createQuery("from ItemPrecio where (producto.nombre like :nombre or producto.codigo =:codigo)  and precio =:precio", ItemPrecio.class)
         .setParameter("nombre", "%" + nombreCodigo + "%")
         .setParameter("codigo", nombreCodigo)
         .setParameter("precio", precio)
         .getResultList();
   }

   @Override
   public ItemPrecio buscarPrecioPorCodigo(String codigo, Precio precio) {
      return (ItemPrecio)this.manager
         .createQuery("from ItemPrecio where producto.codigo = :codigo and precio = :precio", ItemPrecio.class)
         .setParameter("codigo", codigo)
         .setParameter("precio", precio)
         .getSingleResult();
   }

   @Override
   public List<ItemPrecio> buscarPorProducto(Producto producto) {
      return this.manager.createQuery("from ItemPrecio where producto = :producto", ItemPrecio.class).setParameter("producto", producto).getResultList();
   }

   @Override
   public BigDecimal getPrecioProducto(Precio precio, Producto producto) {
      return (BigDecimal)this.manager
         .createQuery("select precioProducto from ItemPrecio where producto =:producto and precio =:precio", BigDecimal.class)
         .setParameter("producto", producto)
         .setParameter("precio", precio)
         .getSingleResult();
   }
}
