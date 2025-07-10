package com.meta.repository.helper.consorcio;

import com.meta.dto.ClienteConsorcioDTO;
import com.meta.modelo.Consorcio;
import java.util.List;

public interface ConsorciosQueries {
   List<Consorcio> getConsorcios(Consorcio consorcio);

   List<Consorcio> getConsorciosPendientes();

   List<ClienteConsorcioDTO> getItemConsorcioByClienteConsorcio(Long consorcio, String cliente);
}
