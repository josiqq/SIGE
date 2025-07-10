package com.meta.repository.helper.condicion;

import com.meta.modelo.Condicion;
import java.util.List;

public interface CondicionesQueries {
   Condicion getCondicionById(Long id);

   List<Condicion> getCondicionDistintoEfectivo();
}
