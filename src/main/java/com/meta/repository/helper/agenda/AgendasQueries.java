package com.meta.repository.helper.agenda;

import com.meta.modelo.Agenda;
import com.meta.modelo.Vendedor;
import java.time.LocalDate;
import java.util.Optional;

public interface AgendasQueries {
   Agenda buscarPorVendedorFecha(Long id);

   Optional<Long> buscarIdAgenda(Vendedor vendedor, LocalDate fecha);
}
