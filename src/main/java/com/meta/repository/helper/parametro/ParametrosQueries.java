package com.meta.repository.helper.parametro;

import com.meta.modelo.Parametro;

public interface ParametrosQueries {
   Long cantParametro(Long id);

   Parametro recuperarParametro();

   Parametro getParametro();
}
