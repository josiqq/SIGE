package com.meta.repository.helper.cajero;

import com.meta.modelo.Cajero;
import com.meta.repository.filter.CajeroFilter;
import java.util.List;

public interface CajerosQueries {
   List<Cajero> buscarCajerosActivos();

   List<Cajero> buscarPorNombreDocumento(CajeroFilter cajeroFilter);
}
