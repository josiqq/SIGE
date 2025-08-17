package com.sige.repository.helper.lote;

import java.util.List;

import com.sige.model.Deposito;
import com.sige.model.Lote;
import com.sige.model.MovimientoLote;
import com.sige.repository.filter.LoteFilter;
import com.sige.repository.filter.MovimientoLoteFilter;

public interface LotesQueries {
   Lote getLoteByNumero(String nroLote);

   Lote getByLoteDepositoProducto(String nroLote, Long idProducto, Deposito deposito);

   List<MovimientoLote> getMovimientoLote(MovimientoLoteFilter filter);

   List<Lote> getLotesByDepositProducto(Long idProducto, Deposito deposito);

   List<Lote> getLotesLista(LoteFilter filter);
}
