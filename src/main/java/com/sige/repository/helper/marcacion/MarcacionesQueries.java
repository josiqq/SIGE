package com.sige.repository.helper.marcacion;

import com.sige.model.Marcacion;

public interface MarcacionesQueries {
   Marcacion buscarMarcacionAbierto();

   void ModificarPuntoA(Integer Punto);

   void ModificarPuntoB(Integer Punto);

   Long getMaximoId();

   Marcacion getMarcacionTerminado(Long id);
}
