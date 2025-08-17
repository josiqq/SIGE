package com.sige.repository.helper.parametro;

import com.sige.model.Parametro;

public interface ParametrosQueries {
   Long cantParametro(Long id);

   Parametro recuperarParametro();

   Parametro getParametro();
}
