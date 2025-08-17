package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.Agenda;
import com.sige.repository.helper.agenda.AgendasQueries;

public interface Agendas extends JpaRepository<Agenda, Long>, AgendasQueries {
}
