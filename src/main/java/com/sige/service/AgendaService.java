package com.sige.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sige.model.Agenda;
import com.sige.repository.Agendas;

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
