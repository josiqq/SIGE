package com.sige.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ClienteConsorcioMapper {
   public List<ClienteConsorcioDTO> mapearObjecto(List<Object[]> results) {
      List<ClienteConsorcioDTO> items = new ArrayList<>();

      for (Object[] row : results) {
         ClienteConsorcioDTO cliente = new ClienteConsorcioDTO();
         cliente.setId((BigInteger)row[0]);
         cliente.setNombre((String)row[1]);
         items.add(cliente);
      }

      return items;
   }
}
