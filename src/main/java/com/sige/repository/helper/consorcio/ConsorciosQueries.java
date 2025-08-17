package com.sige.repository.helper.consorcio;

import java.util.List;

import com.sige.dto.ClienteConsorcioDTO;
import com.sige.model.Consorcio;

public interface ConsorciosQueries {
   List<Consorcio> getConsorcios(Consorcio consorcio);

   List<Consorcio> getConsorciosPendientes();

   List<ClienteConsorcioDTO> getItemConsorcioByClienteConsorcio(Long consorcio, String cliente);
}
