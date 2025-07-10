package com.meta.repository.helper.lote;

import com.meta.modelo.Deposito;
import com.meta.modelo.Lote;
import com.meta.modelo.MovimientoLote;
import com.meta.repository.filter.LoteFilter;
import com.meta.repository.filter.MovimientoLoteFilter;
import java.util.List;

public interface LotesQueries {
   Lote getLoteByNumero(String nroLote);

   Lote getByLoteDepositoProducto(String nroLote, Long idProducto, Deposito deposito);

   List<MovimientoLote> getMovimientoLote(MovimientoLoteFilter filter);

   List<Lote> getLotesByDepositProducto(Long idProducto, Deposito deposito);

   List<Lote> getLotesLista(LoteFilter filter);
}
