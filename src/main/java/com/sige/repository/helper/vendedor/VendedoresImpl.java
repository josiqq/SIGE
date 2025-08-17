package com.sige.repository.helper.vendedor;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Vendedor;
import com.sige.repository.filter.VendedorFilter;

public class VendedoresImpl implements VendedoresQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public BigDecimal buscarSaldo(Long id) {
      return (BigDecimal)this.manager.createQuery("select saldo from Vendedor where id = :id", BigDecimal.class).setParameter("id", id).getSingleResult();
   }

   @Override
   public List<Vendedor> buscarVendedor(VendedorFilter vendedorFilter) {
      return this.manager
         .createQuery("from Vendedor where nombre like :nombre or (documento =:documento or documento is null)", Vendedor.class)
         .setParameter("nombre", "%" + vendedorFilter.getNombreDocumento() + "%")
         .setParameter("documento", vendedorFilter.getNombreDocumento())
         .getResultList();
   }

   @Override
   public List<Vendedor> buscarVendedoresAactivos() {
      return this.manager.createQuery("from Vendedor where activo = true", Vendedor.class).getResultList();
   }

   @Override
   public List<Vendedor> getVendedorSupervisor() {
      String sql = "from Vendedor where supervisor = true and activo = true";
      return this.manager.createQuery(sql, Vendedor.class).getResultList();
   }
}
