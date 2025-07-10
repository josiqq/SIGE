package com.meta.repository.helper.proveedor;

import com.meta.modelo.Proveedor;
import java.util.List;

public interface ProveedoresQueries {
   List<Proveedor> buscarPorNombreODocumento(String nombreDocumento);
}
