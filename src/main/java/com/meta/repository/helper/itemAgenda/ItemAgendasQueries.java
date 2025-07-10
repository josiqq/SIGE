package com.meta.repository.helper.itemAgenda;

import com.meta.modelo.Cliente;
import com.meta.modelo.Estado;

public interface ItemAgendasQueries {
   void modificarItem(Long id, Cliente cliente, Estado estado, String observacio);
}
