package com.sige.repository.helper.condicion;

import java.util.List;

import com.sige.model.Condicion;

public interface CondicionesQueries {
   Condicion getCondicionById(Long id);

   List<Condicion> getCondicionDistintoEfectivo();
}
