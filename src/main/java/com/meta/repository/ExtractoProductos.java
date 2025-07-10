package com.meta.repository;

import com.meta.modelo.ExtractoProducto;
import com.meta.repository.helper.extractoProducto.ExtractoProductosQueries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractoProductos extends JpaRepository<ExtractoProducto, Long>, ExtractoProductosQueries {
}
