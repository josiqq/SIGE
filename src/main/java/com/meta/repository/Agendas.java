package com.meta.repository;

import com.meta.modelo.Agenda;
import com.meta.repository.helper.agenda.AgendasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Agendas extends JpaRepository<Agenda, Long>, AgendasQueries {
}
