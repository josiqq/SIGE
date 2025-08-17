package com.sige.repository.helper.cuenta;

import java.util.List;
import java.util.Optional;

import com.sige.model.Cuenta;
import com.sige.model.Timbrado;

public interface CuentasQueries {
   List<Cuenta> buscarCuentasActivas();

   Optional<Cuenta> getCuentaByTimbrado(Timbrado timbrado);

   Cuenta getCuentaByExpedicion(String uuid);

   List<Cuenta> getCuentaCobranza();

   List<Cuenta> getCuentaNoCobranza();
}
