package com.sige.repository.helper.cuenta;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sige.model.Cuenta;
import com.sige.model.Timbrado;

public class CuentasImpl implements CuentasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public List<Cuenta> buscarCuentasActivas() {
      return this.manager.createQuery("from Cuenta where activo = true", Cuenta.class).getResultList();
   }

   @Override
   public Optional<Cuenta> getCuentaByTimbrado(Timbrado timbrado) {
      String sql = "from Cuenta where timbrado =:timbrado";
      List<Cuenta> result = this.manager.createQuery(sql, Cuenta.class).setParameter("timbrado", timbrado).getResultList();
      return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
   }

   @Override
   public Cuenta getCuentaByExpedicion(String uuid) {
      String sql = "from Cuenta where identificador = :uuid";
      return (Cuenta)this.manager.createQuery(sql, Cuenta.class).setParameter("uuid", uuid).getSingleResult();
   }

   @Override
   public List<Cuenta> getCuentaCobranza() {
      String sql = "from Cuenta where cajaCobranza = true and activo = true";
      return this.manager.createQuery(sql, Cuenta.class).getResultList();
   }

   @Override
   public List<Cuenta> getCuentaNoCobranza() {
      String sql = "from Cuenta where cajaCobranza = false and activo = true";
      return this.manager.createQuery(sql, Cuenta.class).getResultList();
   }
}
