package com.meta.repository.helper.cuenta;

import com.meta.modelo.Cuenta;
import com.meta.modelo.Timbrado;
import java.util.List;
import java.util.Optional;

public interface CuentasQueries {
   List<Cuenta> buscarCuentasActivas();

   Optional<Cuenta> getCuentaByTimbrado(Timbrado timbrado);

   Cuenta getCuentaByExpedicion(String uuid);

   List<Cuenta> getCuentaCobranza();

   List<Cuenta> getCuentaNoCobranza();
}
