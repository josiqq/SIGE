package com.meta.repository.helper.pago;

import com.meta.modelo.Pago;
import com.meta.repository.filter.PagoFilter;
import java.util.List;

public interface PagosQueries {
   List<Pago> getPagos(PagoFilter pagoFilter);
}
