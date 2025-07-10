package com.meta.repository.helper.itemConsorcioImporte;

import com.meta.modelo.Cliente;
import com.meta.modelo.Consorcio;
import com.meta.modelo.Cuenta;
import com.meta.modelo.ItemConsorcioImporte;
import java.math.BigDecimal;
import java.util.List;

public interface ItemConsorcioImportesQueries {
   List<ItemConsorcioImporte> getItemConsorcioImporteByCliente(Cliente cliente, Consorcio consorcio);

   void updateImporte(Long id, BigDecimal montoCobrado, BigDecimal saldo, Cuenta cuenta);
}
