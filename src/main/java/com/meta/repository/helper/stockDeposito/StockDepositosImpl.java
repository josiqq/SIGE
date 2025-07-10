package com.meta.repository.helper.stockDeposito;

import com.meta.modelo.Deposito;
import com.meta.modelo.Producto;
import com.meta.modelo.StockDeposito;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class StockDepositosImpl implements StockDepositosQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<StockDeposito> stock(Producto producto) {
      return this.manager.createQuery("from StockDeposito where producto = :producto", StockDeposito.class).setParameter("producto", producto).getResultList();
   }

   @Override
   public BigDecimal getCantidadByProductoDeposito(Producto producto, Deposito deposito) {
      String sql = "select cantidad from StockDeposito where producto =:producto and deposito =:deposito";
      BigDecimal cantidad = BigDecimal.ZERO;

      try {
         cantidad = (BigDecimal)this.manager
            .createQuery(sql, BigDecimal.class)
            .setParameter("producto", producto)
            .setParameter("deposito", deposito)
            .getSingleResult();
      } catch (NoResultException var6) {
         cantidad = BigDecimal.ZERO;
      }

      return cantidad;
   }
}
