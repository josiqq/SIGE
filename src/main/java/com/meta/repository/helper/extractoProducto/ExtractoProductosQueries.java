package com.meta.repository.helper.extractoProducto;

import com.meta.modelo.ExtractoProducto;
import com.meta.repository.filter.ExtractoProductoFilter;
import java.util.List;

public interface ExtractoProductosQueries {
   List<ExtractoProducto> buscarExtracto(ExtractoProductoFilter extractoProductoFilter);
}
