package com.meta.repository.helper.deposito;

import com.meta.modelo.Deposito;
import java.util.List;

public interface DepositosQueries {
   List<Deposito> buscarDeposito(Deposito deposito);

   List<Deposito> buscarDepositoActivo();
}
