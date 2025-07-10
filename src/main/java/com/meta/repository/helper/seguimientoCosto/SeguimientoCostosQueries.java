package com.meta.repository.helper.seguimientoCosto;

import com.meta.modelo.SeguimientoCosto;
import com.meta.repository.filter.SeguimientoCostoFilter;
import java.util.List;

public interface SeguimientoCostosQueries {
   List<SeguimientoCosto> buscarSeguimieto(SeguimientoCostoFilter seguimientoCostoFilter);
}
