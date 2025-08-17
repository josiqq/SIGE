package com.sige.repository.helper.claseGrupal;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.sige.model.ClaseGrupal;

public class ClaseGrupalesImpl implements ClaseGrupalesQueries {
   @Autowired
   private EntityManager manager;

   @Override
   public List<ClaseGrupal> listaDeClases(ClaseGrupal claseGrupal) {
      return this.manager
         .createQuery(
            "from ClaseGrupal where (horaDesde >= :horaDesde or :horaDesde is null)  and (horaHasta <= :horaHasta or :horaHasta is null) and (instructor = :instructor or :instructor is null)  and (id = :id or :id is null)",
            ClaseGrupal.class
         )
         .setParameter("horaDesde", claseGrupal.getHoraDesde())
         .setParameter("horaHasta", claseGrupal.getHoraHasta())
         .setParameter("instructor", claseGrupal.getInstructor())
         .setParameter("id", claseGrupal.getId())
         .getResultList();
   }
}
