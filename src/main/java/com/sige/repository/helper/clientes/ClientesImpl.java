package com.sige.repository.helper.clientes;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Cliente;
import com.sige.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Cliente> buscarPorNombreODocumento(String nombreDocumento) {
      return this.manager
         .createQuery("from Cliente where (nombre like :nombreDocumento or documento like :nombreDocumento) and activo = true", Cliente.class)
         .setParameter("nombreDocumento", "%" + nombreDocumento + "%")
         .getResultList();
   }

   @Override
   public Long cantidadCliente() {
      return (Long)this.manager.createQuery("select count(id) from Cliente where activo = true", Long.class).getSingleResult();
   }

   @Override
   public List<Cliente> buscarCliente(ClienteFilter clienteFilter) {
      return this.manager
         .createQuery(
            "from Cliente where (nombre like :nombreDocumento or :nombreDocumento is null)or(documento like :nombreDocumento or :nombreDocumento is null)",
            Cliente.class
         )
         .setParameter("nombreDocumento", "%" + clienteFilter.getNombreDocumento() + "%")
         .getResultList();
   }

   @Override
   public BigDecimal buscarSaldo(Long id) {
      return (BigDecimal)this.manager.createQuery("select saldo from Cliente where id = :id", BigDecimal.class).setParameter("id", id).getSingleResult();
   }

   @Override
   public List<Cliente> getClientesByNombreDocumento(String nombreDocumento) {
      String sql = "from Cliente where (documento = :documento or :documento is null) or (nombre like :nombre or :nombre is null) and activo = true";
      return this.manager
         .createQuery(sql, Cliente.class)
         .setParameter("documento", nombreDocumento)
         .setParameter("nombre", "%" + nombreDocumento + "%")
         .getResultList();
   }
}
