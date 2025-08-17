package com.sige.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sige.model.ItemAgenda;
import com.sige.repository.helper.itemAgenda.ItemAgendasQueries;

public interface ItemAgendas extends JpaRepository<ItemAgenda, Long>, ItemAgendasQueries {
}
