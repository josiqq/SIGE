package com.sige.repository.helper.extractoProducto;

import java.util.List;

import com.sige.model.ExtractoProducto;
import com.sige.repository.filter.ExtractoProductoFilter;

public interface ExtractoProductosQueries {
   List<ExtractoProducto> buscarExtracto(ExtractoProductoFilter extractoProductoFilter);
}
