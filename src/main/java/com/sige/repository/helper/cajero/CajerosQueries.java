package com.sige.repository.helper.cajero;

import java.util.List;

import com.sige.model.Cajero;
import com.sige.repository.filter.CajeroFilter;

public interface CajerosQueries {
   List<Cajero> buscarCajerosActivos();

   List<Cajero> buscarPorNombreDocumento(CajeroFilter cajeroFilter);
}
