package com.sige.repository.helper.deposito;

import java.util.List;

import com.sige.model.Deposito;

public interface DepositosQueries {
   List<Deposito> buscarDeposito(Deposito deposito);

   List<Deposito> buscarDepositoActivo();
}
