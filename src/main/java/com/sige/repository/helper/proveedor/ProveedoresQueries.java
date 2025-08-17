package com.sige.repository.helper.proveedor;

import java.util.List;

import com.sige.model.Proveedor;

public interface ProveedoresQueries {
   List<Proveedor> buscarPorNombreODocumento(String nombreDocumento);
}
