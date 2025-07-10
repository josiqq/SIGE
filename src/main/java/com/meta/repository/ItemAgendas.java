package com.meta.repository;

import com.meta.modelo.ItemAgenda;
import com.meta.repository.helper.itemAgenda.ItemAgendasQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemAgendas extends JpaRepository<ItemAgenda, Long>, ItemAgendasQueries {
}
