package com.sige.repository.helper.pago;

import java.util.List;

import com.sige.model.Pago;
import com.sige.repository.filter.PagoFilter;

public interface PagosQueries {
   List<Pago> getPagos(PagoFilter pagoFilter);
}
