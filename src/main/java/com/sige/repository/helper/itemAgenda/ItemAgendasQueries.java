package com.sige.repository.helper.itemAgenda;

import com.sige.model.Cliente;
import com.sige.model.Estado;

public interface ItemAgendasQueries {
   void modificarItem(Long id, Cliente cliente, Estado estado, String observacio);
}
