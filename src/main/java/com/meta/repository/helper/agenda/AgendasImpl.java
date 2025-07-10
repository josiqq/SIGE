package com.meta.repository.helper.agenda;

import com.meta.modelo.Agenda;
import com.meta.modelo.Vendedor;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AgendasImpl implements AgendasQueries {
   @PersistenceContext
   private EntityManager manager;

   @Override
   public Agenda buscarPorVendedorFecha(Long id) {
      return (Agenda)this.manager.createQuery("from Agenda where id = :id", Agenda.class).setParameter("id", id).getSingleResult();
   }

   @Override
   public Optional<Long> buscarIdAgenda(Vendedor vendedor, LocalDate fecha) {
      return Optional.ofNullable(
         (Long)this.manager
            .createQuery("select id from Agenda where vendedor = :vendedor and fecha = :fecha", Long.class)
            .setParameter("vendedor", vendedor)
            .setParameter("fecha", fecha)
            .getSingleResult()
      );
   }
}
