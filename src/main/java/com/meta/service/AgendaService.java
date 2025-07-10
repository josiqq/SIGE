package com.meta.service;

import com.meta.modelo.Agenda;
import com.meta.repository.Agendas;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
   @Autowired
   private Agendas agendas;

   public void guardar(Agenda agenda) {
      this.agendas.save(agenda);
   }

   public Optional<Long> cargaDirecta(Agenda agenda) {
      this.agendas.save(agenda);
      return Optional.ofNullable(agenda.getId());
   }
}
