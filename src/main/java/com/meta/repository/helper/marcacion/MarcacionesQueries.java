package com.meta.repository.helper.marcacion;

import com.meta.modelo.Marcacion;

public interface MarcacionesQueries {
   Marcacion buscarMarcacionAbierto();

   void ModificarPuntoA(Integer Punto);

   void ModificarPuntoB(Integer Punto);

   Long getMaximoId();

   Marcacion getMarcacionTerminado(Long id);
}
