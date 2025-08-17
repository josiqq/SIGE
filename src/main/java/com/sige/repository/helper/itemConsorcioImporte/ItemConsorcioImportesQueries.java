package com.sige.repository.helper.itemConsorcioImporte;

import java.math.BigDecimal;
import java.util.List;

import com.sige.model.Cliente;
import com.sige.model.Consorcio;
import com.sige.model.Cuenta;
import com.sige.model.ItemConsorcioImporte;

public interface ItemConsorcioImportesQueries {
   List<ItemConsorcioImporte> getItemConsorcioImporteByCliente(Cliente cliente, Consorcio consorcio);

   void updateImporte(Long id, BigDecimal montoCobrado, BigDecimal saldo, Cuenta cuenta);
}
