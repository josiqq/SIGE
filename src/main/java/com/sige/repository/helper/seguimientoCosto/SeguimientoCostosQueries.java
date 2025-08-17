package com.sige.repository.helper.seguimientoCosto;

import java.util.List;

import com.sige.model.SeguimientoCosto;
import com.sige.repository.filter.SeguimientoCostoFilter;

public interface SeguimientoCostosQueries {
   List<SeguimientoCosto> buscarSeguimieto(SeguimientoCostoFilter seguimientoCostoFilter);
}
