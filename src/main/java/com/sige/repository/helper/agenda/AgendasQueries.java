package com.sige.repository.helper.agenda;

import java.time.LocalDate;
import java.util.Optional;

import com.sige.model.Agenda;
import com.sige.model.Vendedor;

public interface AgendasQueries {
   Agenda buscarPorVendedorFecha(Long id);

   Optional<Long> buscarIdAgenda(Vendedor vendedor, LocalDate fecha);
}
