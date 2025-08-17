package com.sige.repository.helper.lote;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Deposito;
import com.sige.model.Lote;
import com.sige.model.MovimientoLote;
import com.sige.repository.filter.LoteFilter;
import com.sige.repository.filter.MovimientoLoteFilter;

public class LotesImpl implements LotesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Lote getLoteByNumero(String nroLote) {
      String sql = "from Lote where nroLote = :nroLote";
      new Lote();
      new ArrayList();
      List<Lote> lotes = this.manager.createQuery(sql, Lote.class).setParameter("nroLote", nroLote).getResultList();
      Lote lote;
      if (!lotes.isEmpty()) {
         lote = (Lote)lotes.get(0);
      } else {
         lote = new Lote();
      }

      return lote;
   }

   @Override
   public Lote getByLoteDepositoProducto(String nroLote, Long idProducto, Deposito deposito) {
      String sql = "from Lote where nroLote = :nroLote and producto.id = :idProducto and deposito = :deposito";
      new Lote();

      Lote lote;
      try {
         lote = (Lote)this.manager
            .createQuery(sql, Lote.class)
            .setParameter("nroLote", nroLote)
            .setParameter("idProducto", idProducto)
            .setParameter("deposito", deposito)
            .getSingleResult();
      } catch (Exception var7) {
         lote = new Lote();
      }

      return lote;
   }

   @Override
   public List<MovimientoLote> getMovimientoLote(MovimientoLoteFilter filter) {
      String sql = "from MovimientoLote where fecha between(:fechaDesde)and(:fechaHasta)  and (producto = :producto or :producto is null)  and (deposito = :deposito or :deposito is null)";
      return this.manager
         .createQuery(sql, MovimientoLote.class)
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setParameter("producto", filter.getProducto())
         .setParameter("deposito", filter.getDeposito())
         .getResultList();
   }

   @Override
   public List<Lote> getLotesByDepositProducto(Long idProducto, Deposito deposito) {
      String sql = "from Lote where producto.id = :id and deposito = :deposito and cantidad>0";
      return this.manager.createQuery(sql, Lote.class).setParameter("id", idProducto).setParameter("deposito", deposito).getResultList();
   }

   @Override
   public List<Lote> getLotesLista(LoteFilter filter) {
      String sql = "from Lote where (producto = :producto or :producto is null)  and (vencimiento between(:fechaDesde) and (:fechaHasta)or(:fechaDesde is null and :fechaHasta is null) )  and (deposito = :deposito or :deposito is null) and nroLote like :nroLote";
      return this.manager
         .createQuery(sql, Lote.class)
         .setParameter("producto", filter.getProducto())
         .setParameter("fechaDesde", filter.getFechaDesde())
         .setParameter("fechaHasta", filter.getFechaHasta())
         .setParameter("deposito", filter.getDeposito())
         .setParameter("nroLote", "%" + filter.getNroLote() + "%")
         .getResultList();
   }
}
